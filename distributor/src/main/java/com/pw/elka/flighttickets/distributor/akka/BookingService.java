package com.pw.elka.flighttickets.distributor.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import com.pw.elka.flighttickets.distributor.akka.messages.BookMessage;
import com.pw.elka.flighttickets.distributor.dao.StorageService;
import com.pw.elka.flighttickets.model.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingService {

    @Autowired
    private ActorRef actorRef;

    @Autowired
    private ActorSystem system;

    @Autowired
    private StorageService storageService;

    public void test() {
        ActorSelection selection = system.actorSelection("akka.tcp://akka-spring-demo@localhost:2002/user/greeter");
        selection.tell(new BookMessage("this is the first book message", new Direction("A", "B")), actorRef);
    }

    public void book(String distributor, Direction direction) {
        int port = storageService.findPortByName(distributor);
        ActorSelection selection = system.actorSelection("akka.tcp://akka-spring-demo@localhost:" + port + "/user/greeter");
        selection.tell(new BookMessage("this is the first book message", direction), actorRef);
    }
}
