package com.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;

public class HttpServiceVerticle extends AbstractVerticle {

    @Override
    public void start(){
        HttpServer server = vertx.createHttpServer();

        server.requestHandler(request -> {
            if("/api/greet".equals(request.path())){
                vertx.eventBus().request("greet.request", "Hello from HTTP Service Verticle ",ar->{
                    if(ar.succeeded()){
                        System.out.println("HTTP Microservice: "+ar.result().body());
                        request.response().end("HTTP Microservice: "+ar.result().body());
                    }
                    else{
                        System.out.println("HTTP Microservice: Greeting failed");
                        request.response().end("HTTP Microservice: Greeting failed");
                    }
                });
            }else{
                request.response().setStatusCode(404).end("Not Found");
            }
        });

        server.listen(8080, ar -> {
            if (ar.succeeded()) {
                System.out.println("HTTP Microservice Server started on port 8080");
            } else {
                System.out.println("HTTP Microservice: Failed to start the server - " + ar.cause());
            }
        });
    }
}
