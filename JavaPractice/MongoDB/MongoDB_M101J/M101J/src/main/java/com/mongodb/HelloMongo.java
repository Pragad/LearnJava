package com.mongodb;

import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.Helpers;

public class HelloMongo {

	public static void main(String[] args) 
	{
		// NOTE 1. Creating MongoClient and setting it up
		// MongoClient is the entry point for Mongo db
		// We can give the server address and port in the mongoClient constructor
		// We can also pass Connection string
		// We also have MongoClientOptions where you can build your client options
		
		MongoClient client = new MongoClient();
		
		// Both the database name and collection name is movies
		MongoDatabase db = client.getDatabase("movies");
		MongoCollection<Document> coll = db.getCollection("movies");
		
		// NOTE 2. Delete the existing collection
		coll.drop();
		
		// NOTE 3. Create a document
		Document movie1 = new Document("title", "Usual Suspects")
						  .append("year", 1999)
						  .append("imdb", "tty1999");
		
		// NOTE 4. Using insertOne()
		coll.insertOne(movie1);

		Document movie2 = new Document("title", "Titanic")
		  		  		  .append("year", 2010)
		  		  		  .append("imdb", "tty2010");
		
		Document movie3 = new Document("title", "Snow Day")
				  		  .append("year", 1990)
				  		  .append("imdb", "tty1990");

		// NOTE 5. Using insertMany() and asList()
		coll.insertMany(Arrays.asList(movie2, movie3));
		
		// NOTE 6. Print Json document using Utility function
		//Helpers.printJson(movie2);
		
		// NOTE 7. Get one document and print it
		Document firstMovie = coll.find().first();
		//Helpers.printJson(firstMovie);
		
		// NOTE 8. Get all documents as list and print them
		List<Document> movieDocs = coll.find().into(new ArrayList<Document>());
		for (Document movieDoc : movieDocs)
		{
			//Helpers.printJson(movieDoc);
		}
		
		// NOTE 9. Iterating through Cursor
		// MongoCursor is used to iterate if the number of collection is large
		MongoCursor<Document> cur = coll.find().iterator();
		try
		{
			while (cur.hasNext())
			{
				Document nextDoc = cur.next();
				//Helpers.printJson(nextDoc);
			}
		}
		finally {
			cur.close();
		}
		
		// NOTE 10. Count the number of collections
		System.out.println("Collection Count: " + coll.count());
		
		// NOTE 11. Filters to find and count
		Bson filter = new Document("title", "Titanic");
		//List<Document> movieDocs = coll.find(filter).into(new ArrayList<Document>());
		System.out.println("Collection Count: " + coll.count(filter));
		
		Bson filter2 = new Document("title", "Titanic")
				  	   .append("year", new Document("$gt", 2000)
				  			   		   .append("$lt", 2015));
		System.out.println("Collection Count: " + coll.count(filter2));
		
		// NOTE 12. Builder for Filters
		Bson filter6 = Filters.eq("year", 2000);
		Bson filter7 = Filters.and(Filters.eq("title", "Titanic"), 
				                   Filters.gt("year", 2000),
				                   Filters.lt("year", 2015));
		
		// NOTE 13. Exclude certain field from displaying it
		// Say we don't want imdb field
		//Bson projection = new Document("imdb", 0); // 0 means DON'T include "imdb"
		Bson projection2 = new Document("imdb", 0)
						   .append("_id", 0); // 0 means DON'T include "imdb"
		List<Document> movieDocs2 = coll.find()
										.projection(projection2)
										.into(new ArrayList<Document>());
		for (Document movieDoc : movieDocs2)
		{
			Helpers.printJson(movieDoc);
		}
	}

}
