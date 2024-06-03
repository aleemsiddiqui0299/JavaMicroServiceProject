import com.google.inject.Guice;
import com.google.inject.Injector;
import config.AppConfig;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import io.vertx.core.Vertx;
import modules.FIrstModule;
import router.RouterVerticle;

public class MainApplication extends Application<AppConfig> {

    @Override
    public void run(AppConfig configuration, Environment environment){
        System.out.println("Inside run");
        Injector injector = Guice.createInjector(new FIrstModule());
        Vertx vertx = Vertx.vertx();
        RouterVerticle verticle = injector.getInstance(RouterVerticle.class);
        vertx.deployVerticle(verticle);
    }

    public static void main(String[] args) throws Exception{
        new MainApplication().run(args);

        //TODO
        //1. tests - mockito
        //2. all handlers as singleton
        //3. security cases - sql injection, attacks secure, des
        //4. version cve
        //5. healthcheck for dependencies
        //6. modularity, use enums for apis
        //7. openapi spec/swagger spec
    }
}
