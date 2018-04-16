package database;

import org.bson.Document;

import com.example.demo.model.CrumblrUser;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Registration {
	public static final String HOST = System.getProperty("mongo.host");
	public static final String DBNAME = "users";
	
	public static void Register(CrumblrUser me){
		
		try{
			MongoClient mongoClient = new MongoClient(HOST, 27107);
			MongoDatabase database = mongoClient.getDatabase(DBNAME);
			//===========================================
			System.out.println("Connnection Succ!");
			//===========================================
			Document theUser = new Document();
			
			theUser.put("username", me.getUsername());
			theUser.put("pasword", me.getPassword());
			theUser.put("name", me.getFirstName());
			theUser.put("email", me.getEmail());
			
			database.getCollection("users");
			MongoCollection<Document> dbUsers = database.getCollection("users");
			
			dbUsers.insertOne(theUser);
		
		} catch (Exception e){
			//Placeholder Exception
			System.out.println("Something went wrong");
		}
	}
}
