package com.service.dao;

import java.util.List;
import javax.ws.rs.PathParam;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.mydomain.service.ServicesFactory;
import com.mydomain.service.Users;


public class UserDao {

	public Users getUser(Integer id) {
			return null;
	}
	
	public boolean validateUser(Users user) {

		Datastore dataStore = ServicesFactory.getMongoDB();

		Query<Users> query = dataStore.createQuery(Users.class).disableValidation();

		query.field("emailId").equal(user.getEmailId());
		query.field("password").equal(user.getPassword());

		Users user_exist = query.get();
		
		if (user_exist == null) {
			return (false);
		} else if (!user_exist.getPassword().toString().equals(user.getPassword().toString())) {
			System.out.println("password case mismatch");
			return (false);
		}
		System.out.println("results: " + user_exist);
		System.out.println("results.count: " + user_exist.getEmailId());
		
		System.out.println("\n SINDHU return True");
		return (true);
	}
	
	public List<Users> getUsers() {
		Datastore dataStore = ServicesFactory.getMongoDB();
		List<Users> users = dataStore.createQuery(Users.class).asList();
		return users;
	}
	
	
	public boolean createUser(Users user){
		Datastore dataStore = ServicesFactory.getMongoDB();
		
		Query<Users> query = dataStore.createQuery(Users.class).disableValidation();
		query.field("emailId").equal(user.getEmailId());
		Users user_exist = query.get();
		
		if (user_exist != null) {
			return (false);
		}
		dataStore.save(user);		
		System.out.println("\n SINDHU return True");
		return (true);
	}
	
	public void updateUser(Users u){
		System.out.println("Updating user: ");
	
	}
	
	public boolean deleteUser(@PathParam("param") Integer id) {
		System.out.println("Deleting user: "+id);
		return false;

	}
}


