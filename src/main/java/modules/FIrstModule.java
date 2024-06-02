package modules;

import com.google.inject.AbstractModule;
import router.handlers.DataHandler;

public class FIrstModule extends AbstractModule {

    @Override
    public void configure(){
//        bind(FirstService.class).to(FirstServiceImpl.class);
        bind(DataHandler.class).asEagerSingleton();
    }

}
