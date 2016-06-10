package com.mydomain.service;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class ServicesFactory {
	
	
	private static ThreadLocal<Datastore> mongoTL = new ThreadLocal<Datastore>();
	
	/**
	 * Method to retrieve a mongo database client from the thread local storage
	 * @return
	 */
	public static Datastore getMongoDB(){
		if(mongoTL.get()==null){
		//	MongoClientURI connectionString = new MongoClientURI("mongodb://173.36.55.70:27017");
		        MongoClientURI connectionString = new MongoClientURI("mongodb://146.148.47.238:27017");
			MongoClient mongoClient = new MongoClient(connectionString);	
			Morphia morphia = new Morphia();
			morphia.mapPackage("com.mydomain.service");
			Datastore datastore = morphia.createDatastore(mongoClient, "blog");
			datastore.ensureIndexes();
			mongoTL.set(datastore);
			return datastore;
		}
		return mongoTL.get();
	}
	
}
