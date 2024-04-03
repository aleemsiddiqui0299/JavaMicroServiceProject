package router;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.RedirectAuthHandler;

public class RouterVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise){
        Future<Void> future = init();
        future.onComplete(asyncResult -> {
            if(asyncResult.failed()){
                startPromise.fail(asyncResult.cause().getMessage());
            }
            else{
                startPromise.complete();
            }
        });
    }
    private Router createRouter(){
        Router router = Router.router(vertx);

        router.route("/api/listServices").handler(new RedirectAuthHandler() {
            @Override
            public void handle(RoutingContext routingContext) {
                System.out.println("Incoming request handler called");
            }
        });
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