package com.mydomain.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.utils.IndexType;

@Entity("posts")
@Indexes(
	 @Index(fields ={@Field("id"), @Field(value = "postTitle", type = IndexType.TEXT), @Field(value = "postAbout", type = IndexType.TEXT)})
	//	@Index(fields ={@Field("id"), @Field(value = "$**", type = IndexType.TEXT)})

   // @Index(fields ={@Field("id")})
)
public class Posts {
	@Id
	private ObjectId id;
	private String postTitle="";
	private Date  postDate;
	private String postAbout="";
	private String postTags="";
	private List<Comments> comment;
	private String postEmail="";
	
//	private int userId;

	public Posts(){}
	   public Posts(String postTitle, String postAbout, String tags) {
		   this.postTitle = postTitle;
		   this.postAbout = postAbout;
		   this.postTags = tags;
		   this.postDate = new Date();
		   this.comment = new ArrayList<Comments>();
	   }
	   
	public void setId( ObjectId id ) {
		   this.id = id;
	}
	   public String getId() {
		      return id.toString();
		   }

	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostAbout() {
		return postAbout;
	}
	public void setPostAbout(String postAbout) {
		this.postAbout = postAbout;
	}
	public String getpostTags() {
		return postTags;
	}
	
	public void setPostTags(String postTags) {
		this.postTags = postTags;
	}
	
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		 this.postDate = postDate;
	}
	
	public List<Comments> getComment() {
		return comment;
	}
	public void setComment( List postId ) {
		this.comment = postId;
	}
	
	public String getPostEmail() {
		return postEmail;
	}
	public void setPostEmail( String postEmail ) {
		this.postEmail = postEmail;
	}

	@Override
	public String toString() {
		return "posts [Title=" +  postTitle+", about=" + postAbout +"tag = "+postTags+"date = "+postDate+"]" + "Comments = "+comment ;
	}

}
