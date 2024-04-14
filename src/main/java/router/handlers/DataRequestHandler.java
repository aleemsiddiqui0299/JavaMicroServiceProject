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
//        try {
////            Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:testdb","SA","");
//////            String jdbcUrl = "jdbc:hsqldb:hsql://localhost:" + 9001 + "/testdb";
//////            Connection conn = DriverManager.getConnection(jdbcUrl, "SA", "");
////            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM table_name");
////            ResultSet rs = stmt.executeQuery();
////            StringBuilder responseData = new StringBuilder();
////            while(rs.next()) {
////                String data = rs.getString("column_name");
////                responseData.append(data).append("\n");
////            }
////            rs.close();
////            stmt.close();
////            conn.close();
//
//            routingContext.response()
//                    .putHeader("content-type", "text/plain")
//                    .end(responseData.toString());
//        }catch (SQLException e) {
//            System.out.println("Error in sql statement : "+e.getMessage());
//            routingContext.response().setStatusCode(500).end("Internal Server Error");
//        }
    }
}
