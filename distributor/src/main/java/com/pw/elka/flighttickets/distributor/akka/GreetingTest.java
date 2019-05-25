package com.pw.elka.flighttickets.distributor.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import akka.util.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scala.concurrent.Await;
import scala.concurrent.Future;

import javax.annotation.PostConstruct;
import java.time.Duration;

import static com.pw.elka.flighttickets.distributor.akka.cfg.SpringExtension.SPRING_EXTENSION_PROVIDER;

@Component
public class GreetingTest {

    ActorRef greeter;
    @Autowired
    private ActorSystem system;

    @PostConstruct
    public void createGreeter() {
        greeter = system.actorOf(SPRING_EXTENSION_PROVIDER.get(system).props("greetingActor"), "greeter");
    }

    public void test() throws Exception {


        Timeout timeout = Timeout.create(Duration.ofSeconds(5));

        Future<Object> future = Patterns.ask(greeter, new GreetingActor.Greet("John"), timeout);
        String val = (String) Await.result(future, timeout.duration());
        System.out.println(val);


        ActorSelection selection = system.actorSelection("akka.tcp://akka-spring-demo@localhost:2000/user/greeter");
        selection.tell("Pretty awesome feature", greeter);
    }
}
