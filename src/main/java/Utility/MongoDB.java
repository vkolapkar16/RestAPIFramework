package Utility;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import Utility.FrameworkConfig;

import java.io.IOException;

import org.aeonbits.owner.ConfigFactory;
import org.bson.Document;

public class MongoDB
{

public MongoClient getAllCollection() throws IOException 
{
	
	FrameworkConfig cfg = ConfigFactory.create(FrameworkConfig.class, System.getenv(), System.getProperties());		
		String mongo_db_url=cfg.dburl();
		String db="xuritidb";
		MongoClientURI uri = new MongoClientURI(mongo_db_url);
		MongoClient mongoClient = new MongoClient(uri);
		 
		return mongoClient;
	}
	

/* Create Connections
 * public class ConnectionDB {
    public static void establishConnections()
    {
 
        try {
            MongoClient db
                = new MongoClient("localhost", 27017);
 
            MongoCredential credential;
            credential
                = MongoCredential
                      .createCredential(
                          "GFGUser", "mongoDb",
                          "password".toCharArray());
            System.out.println(
                "Successfully Connected"
                + " to the database");
 
            MongoDatabase database
                = db.getDatabase("mongoDb");
            System.out.println("Credentials are: "
                               + credential);
        }
        catch (Exception e) {
            System.out.println(
                "Connection establishment failed");
            System.out.println(e);
        }
    }
}

GET DATA FROM DB
public class Collection {
 
    public static void getCollection(
        String collectionName)
    {
 
        try {
            // establishConnections() Code
            // is defined above
            establishConnections();
 
            // Retrieve the collection
            MongoCollection<Document>
                collection = database
                                 .getCollection(collectionName);
 
            System.out.println(
                "Collection retrieved Successfully");
        }
        catch (Exception e) {
            System.out.println(
                "Collection retrieval failed");
            System.out.println(e);
        }
    }
}
 */
	
}
