package com.example;

import io.vertx.core.AbstractVerticle;

public class EventBusServiceVerticle extends AbstractVerticle {

    @Override
    public void start(){
        vertx.eventBus().consumer("greet.request", message -> {
            vertx.setTimer(1000, timerId -> {
                System.out.println("Event bus message : "+message.body());
                message.reply("Hello from EventBus service!!");
            });
        });
    }
}
