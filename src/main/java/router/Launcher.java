package router;

import io.vertx.core.Vertx;

public class Launcher {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new RouterVerticle(), res->{
            if(res.succeeded()){
                System.out.println("Router verticle deployed successfully");
            }else{
                System.err.println("Failed to deploy verticle: "+res.cause());
            }
        });
    }
} 
