package router.handlers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.vertx.ext.web.RoutingContext;
import org.bson.Document;

import java.sql.*;

public class DataRequestHandler extends BaseHandler{

    @Override
    protected void doHandle(RoutingContext routingContext) {
        System.out.println("GET DATA CALL");
        String mongoConnectionUri = "mongodb+srv://<username>:<password>@<deployment-name>.mongodb.net/";
        try(MongoClient mongoClient = MongoClients.create(mongoConnectionUri)){
            MongoDatabase mongoDatabase = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> collection = mongoDatabase.getCollection("movies");
            Document document = collection.find().first();
            if(document != null) {
                System.out.println("Document receieved : " + document.toJson());
            } else {
                System.out.println("No matching document found");
            }
        }

    }
}
