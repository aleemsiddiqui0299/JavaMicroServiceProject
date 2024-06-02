package router.handlers;

import io.vertx.ext.web.RoutingContext;

public class DataHandler {
    public void handle(RoutingContext ctx){
        ctx.response().putHeader("Content-Type", "text/plain")
                .end("Good evening Sir");

    }
}
