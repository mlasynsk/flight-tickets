package com.pw.elka.flighttickets.distributor.akka;

import akka.actor.UntypedActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GreetingActor extends UntypedActor {

    //mine
    @Autowired
    private GreetingService greetingService;

    // constructor

    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println(message);
        if (message instanceof Greet) {
            String name = ((Greet) message).getName();
            System.out.println("--->Received:" + name);
            getSender().tell(greetingService.greet(name), getSelf());


        } else {
            unhandled(message);
        }
    }

    public static class Greet {

        private String name;

        public Greet(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
// standard constructors/getters

    }
}