package com.pw.elka.flighttickets.distributor.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import com.pw.elka.flighttickets.distributor.akka.messages.BookMessage;
import com.pw.elka.flighttickets.distributor.dao.StorageService;
import com.pw.elka.flighttickets.model.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.pw.elka.flighttickets.distributor.akka.cfg.SpringExtension.SPRING_EXTENSION_PROVIDER;

@Component
public class BookingService {

    @Autowired
    private ActorRef actorRef;

    @Autowired
    private ActorSystem system;

    @Autowired
    private StorageService storageService;

    @PostConstruct
    public void init() {
        //creating sellers
        for (int i = 1; i < 10; i++) {
            system.actorOf(SPRING_EXTENSION_PROVIDER.get(system).props("bookingActor"), "seller"+i);
        }

    }

    public void test() {
        ActorSelection selection = system.actorSelection("akka.tcp://akka-spring-demo@localhost:2002/user/seller");
        selection.tell(new BookMessage("this is the first book message", new Direction("A", "B")), actorRef);
    }

    public void book(String distributor, Direction direction) {
        int port = storageService.findPortByName(distributor);
        ActorSelection selection = system.actorSelection("akka.tcp://akka-spring-demo@localhost:" + port + "/user/seller*");
        selection.tell(new BookMessage("Booking the ticket", direction), actorRef);
    }
}
