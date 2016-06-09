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
import javax.ws.rs.NotAuthorizedException;
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
import com.mydomain.service.Session;
import com.mydomain.service.Users;


public class Blogdao {

	public List<Posts> getTitles() {
		Datastore dataStore = ServicesFactory.getMongoDB();
	
		List<Posts> valueSend = dataStore.find(Posts.class).retrievedFields(true, "postTitle", "postAbout", "id", "postDate", "postEmail").asList();
		//	List<Posts> hotels = dataStore.find(Posts.class).criteria(null)asList();
		List<Posts> valueComments = dataStore.find(Posts.class).retrievedFields(true, "postTitle", "postAbout", "id", "postDate", "postEmail", "comment").asList();
		Iterator itr = valueComments.iterator();
		Iterator itrSend = valueSend.iterator();
	      while(itr.hasNext() && itrSend.hasNext()) {
	         Posts element = (Posts) itr.next();
	         Posts elementSend = (Posts) itrSend.next();
	         if(element.getComment() != null)
	         elementSend.setCount(element.getComment().size());
	         else 
	        	 elementSend.setCount(0);
	         System.out.print("data: " + element + " ");
	      }
		return valueSend;	 
	}

	public boolean updatePost(Posts info) {
		Datastore dataStore = ServicesFactory.getMongoDB();
		ObjectId  oid =  new ObjectId(info.getpostTags());
		Session sess =  dataStore.get(Session.class, oid);
		if (sess == null) {
			throw new NotAuthorizedException("Not valid session");
		}
		info.setPostEmail(sess.getemailId());
		System.out.println("Inserting to Posts db : "+info.toString()+ " \n" );
		info.setPostDate(new Date().toString());
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
		
		ObjectId  oid1 =  new ObjectId(info.getAuthorName());
		Session sess =  dataStore.get(Session.class, oid1);
		if (sess == null) {
			throw new NotAuthorizedException("Not valid session");
		}
		info.setAuthorEmail(sess.getemailId());

		Comments comm = new Comments(info.getContent(), info.getAuthorName(), info.getAuthorEmail());
		
		UpdateOperations<Posts> updateOptions = dataStore.createUpdateOperations(Posts.class);
		updateOptions.add("comment", comm);
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
		Query<Posts> query = dataStore.createQuery(Posts.class).disableValidation().retrievedFields(true, "postTitle", "postAbout", "id", "postDate", "postEmail");
		Query<Posts> valueComments = dataStore.find(Posts.class).retrievedFields(true, "postTitle", "postAbout", "id", "postDate", "postEmail", "comment");

		List<Posts> post= query.field("postAbout").containsIgnoreCase(str).asList();
		List<Posts> valuepost= query.field("postAbout").containsIgnoreCase(str).asList();
		Iterator itrSend = post.iterator();
		Iterator itr = valuepost.iterator();
		 while(itr.hasNext() && itrSend.hasNext()) {
	         Posts element = (Posts) itr.next();
	         Posts elementSend = (Posts) itrSend.next();
	         if(element.getComment() != null)
	         elementSend.setCount(element.getComment().size());
	         else 
	        	 elementSend.setCount(0);
	         System.out.print("data: " + element + " ");
	      }
	      return post;
	}
	
	public boolean blogDeleteStr(String blog_id) {
		Datastore dataStore = ServicesFactory.getMongoDB();
		ObjectId oid = null;
		try {
				oid = new ObjectId(blog_id);
				
			} catch (Exception e) {// Ignore format errors
			}
		Posts post= null;
		post = dataStore.createQuery(Posts.class).field("id")
						.equal(oid).get();
		dataStore.delete(post);
		return true;
		
	}
	public boolean commentDeleteStr(String blog_id) {
		Datastore dataStore = ServicesFactory.getMongoDB();
		ObjectId oid = null;
		try {
				oid = new ObjectId(blog_id);
				
			} catch (Exception e) {// Ignore format errors
			}
		Posts post= null;
		post = dataStore.createQuery(Posts.class).field("comment.commentDate").equal(blog_id).get();
		
		List<Comments> c = post.getComment();
		System.out.println("comments list"+ c);
		
		Iterator itr = c.iterator();
	      while(itr.hasNext()) {
	         break;
	         }
		//dataStore.delete(c);
		return true;
		
	}
}
