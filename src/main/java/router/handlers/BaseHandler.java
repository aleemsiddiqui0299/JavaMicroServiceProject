package router.handlers;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public abstract class BaseHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext routingContext){doHandle(routingContext);}

    protected abstract void doHandle(RoutingContext routingContext);

}