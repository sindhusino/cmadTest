package com.service.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.mydomain.service.Comments;
import com.mydomain.service.Posts;
import com.mydomain.service.ServicesFactory;
import com.mydomain.service.Users;


public class Blogdao {

	public List<Posts> getTitles() {
		Datastore dataStore = ServicesFactory.getMongoDB();
	
		List<Posts> value = dataStore.find(Posts.class).retrievedFields(true, "postTitle", "postAbout", "id").asList();
		//	List<Posts> hotels = dataStore.find(Posts.class).criteria(null)asList();
		Iterator itr = value.iterator();
	      while(itr.hasNext()) {
	         Object element = itr.next();
	         System.out.print("data: " + element + " ");
	      }
		return value;	 
	}

	public boolean updatePost(Posts info) {
		Datastore dataStore = ServicesFactory.getMongoDB();
		System.out.println("Inserting to Posts db : "+info.toString()+ " \n" );
		info.setPostDate(new Date());
		dataStore.save(info);
		System.out.println("\n SINDHU Updated Post return True");
		return (true);
	}
	public boolean updateComment(Comments info, String id) {
		Datastore dataStore = ServicesFactory.getMongoDB();
		ObjectId oid = null;
		try {
				oid = new ObjectId(id);
				
			} catch (Exception e) {// Ignore format errors
			}
		Posts post= null;
		post = dataStore.createQuery(Posts.class).field("id")
						.equal(oid).get();
		if (post == null) {
			return (false);
		}
		
		System.out.println(info.toString());

		Comments comm = new Comments(info.getContent(), info.getAuthorName(), info.getAuthorEmail());
		
		UpdateOperations<Posts> updateOptions = dataStore.createUpdateOperations(Posts.class);
		updateOptions.add("comment", info);
		dataStore.update(post, updateOptions);
		System.out.println("\n SINDHU Updated comment return True");
		return (true);
	}
	public Posts getPage(String id) {
		Datastore dataStore = ServicesFactory.getMongoDB();
		ObjectId oid = null;
		try {
				oid = new ObjectId(id);
				
			} catch (Exception e) {// Ignore format errors
			}
		Posts post= null;
		post = dataStore.createQuery(Posts.class).field("id")
						.equal(oid).get();
		return post;
	}
	
	public List<Posts> blogSearchStr(String str) {
		Datastore dataStore = ServicesFactory.getMongoDB();
	//	List<Posts> value = dataStore.find(Posts.class).retrievedFields(true, "postTitle", "postAbout", "id").asList();
		Query<Posts> query = dataStore.createQuery(Posts.class).disableValidation().retrievedFields(true, "postTitle", "postAbout", "id");

		List<Posts> post= query.field("postAbout").containsIgnoreCase(str).asList();
		Iterator itr = post.iterator();
	      while(itr.hasNext()) {
	         Object element = itr.next();
	         System.out.print("data: " + element + " ");
	      }
	      return post;
	      /*
			Datastore dataStore = ServicesFactory.getMongoDB();
			dataStore.ensureIndexes();
			//List<Posts> post= dataStore.createQuery(Posts.class).search(str).asList();
			
			List<Posts> post= dataStore.createQuery(Posts.class).field("postAbout").containsIgnoreCase(str).asList();
			Iterator itr = post.iterator();
		      while(itr.hasNext()) {
		         Object element = itr.next();
		         System.out.print(element + " ");
		      }
			//find({$text: {$search: "dogs"}}, {score: {$meta: "toextScore"}}).sort({score:{$meta:"textScore"}})
			//post = dataStore.createQuery(Posts.class).field("id")
							//.equal(oid).get();
			return post;
		
		*/
//		Query<Posts> results =dataStore.find();
		
	}
}
