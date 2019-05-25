package com.pw.elka.flighttickets.distributor.akka;


import org.springframework.stereotype.Component;

@Component
public class GreetingService {

    public String greet(String name) {
        return "Hello, " + name;
    }
}