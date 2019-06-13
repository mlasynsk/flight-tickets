package com.pw.elka.flighttickets.distributor.akka;

import akka.actor.UntypedActor;
import com.pw.elka.flighttickets.distributor.akka.messages.BookMessage;
import com.pw.elka.flighttickets.distributor.dao.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BookingActor extends UntypedActor {

    @Autowired
    private GreetingService greetingService;

    @Autowired
    private StorageService storageService;

    public BookingActor() {
        System.out.println("Booking actor created:" + this.toString());
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println(message);
        if (message instanceof Greet) {
            String name = ((Greet) message).getName();
            System.out.println("--->Received:" + name);
            getSender().tell(greetingService.greet(name), getSelf());

        } else if (message instanceof BookMessage) {
            BookMessage bookMessage = (BookMessage) message;
            System.out.println("Received book message: " +  message);
            storageService.book(bookMessage.getDirection());
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

    }
}