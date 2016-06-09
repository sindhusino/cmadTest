package com.mydomain.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

import org.mongodb.morphia.Datastore;

import com.service.dao.UserDao;



@Path("/user")
public class UserService {
	
UserDao dao = new UserDao();
	
	public void setUserDao(UserDao dao){
		this.dao = dao;
	}
	@POST
	@Path("/check/email")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response validateUser(Users u) {	
		System.out.println("Validate User : "+u.toString()+ " exists\n" );
		boolean validate;
		
		try {
			Users user = new Users();
			user.setEmailId(u.getEmailId());
			user.setPassword(u.getPassword());
			validate = dao.validateUser(user);
			if(validate) {
				System.out.println("\n SINDHU Validated Successfully");
				//Response.ok(user, MediaType.APPLICATION_JSON).build();

				// Issue a token for the user
				String token = issueToken(user.getEmailId());
				user.setTokenValue(token);
				System.out.println("token"+ token);
				return Response.ok(user, MediaType.APPLICATION_JSON).build();
            //return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).build();
			}
			else { 
				System.out.println("\n SINDHU Not validated");
				return Response.status(Response.Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON).build();
			}

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } 
	}
	
	@GET
	@Secured
	@Path("/blogs")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Users> getUser() {
		System.out.println("get blog contents" );
		List<Users> listUser = new ArrayList<Users>();
 		Users newUser = new Users();
		newUser.setEmailId("blogemail");
		newUser.setname("blog");
		newUser.setPassword("blogpassword");
		listUser.add(newUser);
		return listUser;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Users> getUsers() {
		return dao.getUsers();
	}
	
	
	@POST 
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(Users u){
		boolean created_row;
		System.out.println("Creating user: "+u.getname()+"  , Email = "+u.getEmailId()+" password: "+u.getPassword());
		Users user = new Users();
		user.setEmailId(u.getEmailId());
		user.setname(u.getname());
		user.setPassword(u.getPassword());
		created_row = dao.createUser(user);
		
		if(created_row) {
			System.out.println("\n ISNDHU inside created row");
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).build();
		}
		else { 
			System.out.println("\n ISNDHU outside created row");
			return Response.status(Response.Status.CONFLICT).type(MediaType.APPLICATION_JSON).build();
		}
		
	}

	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	//public void updateUser(@FormParam("id") Integer id, @FormParam("name") String name,@FormParam("age") Integer age,@FormParam("emailId") String emailId){
	public void updateUser(Users u){
		System.out.println("Updating user: "+u.getname());
	
	}
	
    private String issueToken(String username) {
    	Datastore dataStore = ServicesFactory.getMongoDB();
    	
    	Session session = dataStore.createQuery(Session.class).filter("username =", username ).get();
    	
    	if (session == null) {
    		session = new Session();
    		session.setemailId(username);
    	}
    	
    	session.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
    	
    	dataStore.save(session);
    	
    	Session sess = dataStore.createQuery(Session.class).filter("username =", username ).get(); 
    	
    	return sess.getId();
    }
    
    
}