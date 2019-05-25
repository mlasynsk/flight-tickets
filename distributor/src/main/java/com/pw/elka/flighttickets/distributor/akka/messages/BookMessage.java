package com.pw.elka.flighttickets.distributor.akka.messages;

import com.pw.elka.flighttickets.model.Direction;

import java.io.Serializable;

public class BookMessage implements Serializable {
    private Direction direction;
    private String message;

    public BookMessage(String message, Direction direction) {
        this.message = message;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
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
                "direction=" + direction +
                ", message='" + message + '\'' +
                '}';
    }
}
