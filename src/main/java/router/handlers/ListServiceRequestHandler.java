package router.handlers;

import io.vertx.ext.web.RoutingContext;

public class ListServiceRequestHandler extends BaseHandler{

    @Override
    protected void doHandle(RoutingContext routingContext){
        System.out.println("List Service api hit");
        System.out.println("Routing Context : "+routingContext);
    }
}
