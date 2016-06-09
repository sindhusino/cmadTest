package com.mydomain.service;

import java.util.Date;

public class Comments {
	private String authorName="";
	private String authorEmail="";
	private int commentId;
	private String content="";
	private Date commentDate;

	public Comments(){}
	public Comments(String content , String name, String email) {
		   this.authorEmail = email;
		   this.authorName = name;
		   this.content = content;
		   this.commentDate = new Date();
   }
	public void setCommentId( int id ) {
		      this.commentId = id;
	}
	public int getCommentId() {
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
	
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		 this.commentDate = commentDate;
	}
	
	@Override
	public String toString() {
		return "comment [author" +  authorName+", author email = " + authorEmail +"content = "+content+"date = "+commentDate+"]";
	}

}
