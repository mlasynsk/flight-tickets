package com.pw.elka.flighttickets.distributor.akka.messages;

public class BookMessage {
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
