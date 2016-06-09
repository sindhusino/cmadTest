package com.mydomain.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Hashtable;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.service.dao.Blogdao;

@Path("/page")
public class BlogService {
	
Blogdao dao = new Blogdao();
	
	public void setBlogDao(Blogdao dao){
		this.dao = dao;
	}
	
	@POST
	@Secured
	@Path("/blogpage")
	@Consumes({MediaType.APPLICATION_JSON})
	public void updatePost(Posts info) {	
		System.out.println("Insert to Posts db : "+info.toString()+ " \n" );
		dao.updatePost(info);
	}
	
	@GET
	@Secured
	@Produces({MediaType.APPLICATION_JSON})
	public List<Posts> getTitlesPage() {
		return dao.getTitles();
	}
	@GET
	@Secured
	@Path("/blogpagetitle/{param}")
	@Produces({MediaType.APPLICATION_JSON})
	public Posts getBlogPage(@PathParam("param") String param) {
		System.out.println("check if : "+param+ " exists\n" );
		return dao.getPage(param);
	}
	
	@POST
	@Secured
	@Path("/comment/{param}")
	@Consumes({MediaType.APPLICATION_JSON})
	public void updateComment(Comments comment,@PathParam("param") String param) {
		System.out.println("Insert to Comments db : "+param+ " \n" );
		System.out.println("content :" + comment);
		dao.updateComment(comment,param);
	}
	@POST
	@Secured
	@Path("/search/{param}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Posts> blogSearch(@PathParam("param") String param) {
		System.out.println("Inside Search  : "+param+ " \n" );
		return (dao.blogSearchStr(param));
	}
	
	@DELETE
	@Secured
	@Path("delete")
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean deleteUser(@QueryParam("id") Integer id) {
		System.out.println("Deleting user: " + id);

		return true;
	}

	@DELETE
	@Secured
	@Path("comment/delete")
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean deletecomment(@QueryParam("id") Integer id) {
		System.out.println("Deleting comment: " + id);

		return true;
	}
	
}
