package router.handle_connections;
import static com.mongodb.client.model.Filters.eq;

import com.mongodb.*;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MoviesDataConnection {
    public static void main(String[] args) {
        System.out.println("GET DATA CALL");
        String connectionString="mongodb+srv://<username>:<password>@<deployment-name>.mongodb.net/";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                System.out.println("Getting db");
                MongoDatabase database = mongoClient.getDatabase("sample_mflix");
                System.out.println("Getting collection");
                MongoCollection<Document> collection = database.getCollection("movies");
                System.out.println("getting document");
                Document document = collection.find().first();
                if(document != null) {
                    System.out.println("Document receieved : " + document.toJson());
                } else {
                    System.out.println("No matching document found");
                }
                database.runCommand(new Document("ping", 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException e) {
                System.out.println("Exception "+e);
//                e.printStackTrace();
            }
        }
    }
}
