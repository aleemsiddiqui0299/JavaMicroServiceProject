package router.handlers;

import io.vertx.ext.web.RoutingContext;

import java.sql.*;

public class DataRequestHandler extends BaseHandler{

    @Override
    protected void doHandle(RoutingContext routingContext) {
        System.out.println("GET DATA CALL");
        try {
            Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:testdb","SA","");
//            String jdbcUrl = "jdbc:hsqldb:hsql://localhost:" + 9001 + "/testdb";
//            Connection conn = DriverManager.getConnection(jdbcUrl, "SA", "");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM table_name");
            ResultSet rs = stmt.executeQuery();
            StringBuilder responseData = new StringBuilder();
            while(rs.next()) {
                String data = rs.getString("column_name");
                responseData.append(data).append("\n");
            }
            rs.close();
            stmt.close();
            conn.close();

            routingContext.response()
                    .putHeader("content-type", "text/plain")
                    .end(responseData.toString());
        }catch (SQLException e) {
            System.out.println("Error in sql statement : "+e.getMessage());
            routingContext.response().setStatusCode(500).end("Internal Server Error");
        }
    }
}
