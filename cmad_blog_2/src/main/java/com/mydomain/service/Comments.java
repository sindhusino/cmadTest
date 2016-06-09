package com.mydomain.service;

import java.util.Date;

public class Comments {
	private String authorName="";
	private String authorEmail="";
	private Date commentId;
	private String content="";
	private String commentDate;

	public Comments(){}
	public Comments(String content , String name, String email) {
		   this.authorEmail = email;
		   this.authorName = name;
		   this.content = content;
		   this.commentDate = new Date().toString();
		   this.commentId = new Date();
   }
	public void setCommentId( String id ) {
		      this.commentId = new Date();
	}
	public Date getCommentId() {
		return commentId;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}
	public void setAuthorEmail(String emailId) {
		this.authorEmail = emailId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthorName() {
		return authorName;
	}
	
	public void setAuthorName(String name) {
		this.authorName = name;
	}
	
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		 this.commentDate = new Date().toString();
	}
	
	@Override
	public String toString() {
		return "comment [author" +  authorName+", author email = " + authorEmail +"content = "+content+"date = "+commentDate+"]";
	}

}
