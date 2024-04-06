package router;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import router.handlers.DataRequestHandler;
import router.handlers.ListServiceRequestHandler;

public class RouterVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise){
        Future<Void> future = init();
        future.onComplete(asyncResult -> {
            if(asyncResult.failed()){
                startPromise.fail(asyncResult.cause().getMessage());
            }
            else{
                System.out.println("Http server async response success");
                startPromise.complete();
            }
        }).onFailure(
                failure ->{
                    System.out.println("Failed to start HTTP server on port 8080... "+failure);
                }
        );
    }
    private Router createRouter(){
        Router router = Router.router(vertx);

        //enabling cors from server side
        router.route().handler(routingContext ->{
            routingContext.response()
                    .putHeader("Access-Control-Allow-Origin","*")
                    .putHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE")
                    .putHeader("Access-Control-Allow-Headers","content-type");
            routingContext.next();
        });
        router.get("/").handler(routingContext -> {
            routingContext.response().end("Hello User");
        });
        router.get("/api/listServices").handler(new ListServiceRequestHandler());
        router.get("/api/data").handler(new DataRequestHandler());
//        router.route("/api/listServices").handler(new RedirectAuthHandler() {
//            @Override
//            public void handle(RoutingContext routingContext) {
//                System.out.println("Incoming request handler called");
//            }
//        });
        return router;
    }

    private Future<Void> init(){
        Promise<Void> promise = Promise.promise();
        Router router = createRouter();
        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8080, result -> {
                    if (result.succeeded()) {
                        System.out.println("Server started on port 8080");
                        promise.complete();
                    } else {
                        promise.fail(result.cause());
                    }
                });
        return promise.future();
    }
}