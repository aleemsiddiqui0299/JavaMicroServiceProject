package router.handle_connections;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MoviCollectionWithCache {
    private static final String connectionString="mongodb+srv://<username>:<password>@<deployment-name>.mongodb.net/";
    private static final ServerApi serverApi = ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build();
    private static final MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(connectionString))
            .serverApi(serverApi)
            .build();

    private static final MongoClient mongoClient = MongoClients.create(settings);
    private static final LoadingCache<String , Document> cache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build(new CacheLoader<String, Document>() {
                @Override
                public Document load(String key) throws Exception {
                    return getDataFromDatabase(key);
                }
            });
    public static Document getData(String key) throws ExecutionException {
        return cache.get(key);
    }

    private static Document getDataFromDatabase(String key){
        MongoDatabase database = mongoClient.getDatabase("sample_mflix");
        System.out.println("Getting collection");
        MongoCollection<org.bson.Document> collection = database.getCollection("movies");
        System.out.println("getting document");
        Document document = collection.find().first();
        if(document != null) {
            System.out.println("Document receieved : " + document.toJson());
        } else {
            System.out.println("No matching document found");
        }
        database.runCommand(new org.bson.Document("ping", 1));
        System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
        return document;
    }


}
