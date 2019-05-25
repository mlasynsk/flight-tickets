package com.pw.elka.flighttickets.distributor.akka.messages;

import java.io.Serializable;

public class BookMessage implements Serializable {
    private String message;

    public BookMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BookMessage{" +
                "message='" + message + '\'' +
                '}';
    }
}
