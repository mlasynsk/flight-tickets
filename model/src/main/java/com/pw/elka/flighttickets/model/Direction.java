package com.pw.elka.flighttickets.model;

public class Direction {
    private String from;
    private String to;

    public Direction() {
    }

    public Direction(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
