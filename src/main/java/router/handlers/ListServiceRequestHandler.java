package router.handlers;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.ArrayList;

//objectives - introduce some tight coupling and async db fetch data
public class ListServiceRequestHandler extends BaseHandler{

    @Override
    protected void doHandle(RoutingContext routingContext){
        System.out.println("List Service api hit");
        System.out.println("Routing Context : "+routingContext);

        ArrayList<String> arr = new ArrayList<>();

        //can fetch from db here
        String[] services = new String[]{"peek", "update", "remove"};
        for(String service: services){
            arr.add(service);
        }
        JsonObject jsonObject = new JsonObject()
                .put("time",System.currentTimeMillis())
                .put("services",new JsonArray(arr));

        routingContext.response()
                .putHeader("Content-Type","application/json")
                .end(jsonObject.encode());
    }
}
