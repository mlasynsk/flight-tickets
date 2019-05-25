package com.pw.elka.flighttickets.distributor.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GreetingTest {

    @Autowired
    ActorRef greeter;
    @Autowired
    private ActorSystem system;

    public void test() {
        ActorSelection selection = system.actorSelection("akka.tcp://akka-spring-demo@localhost:2002/user/greeter");
        selection.tell("Pretty awesome feature", greeter);
    }
}
