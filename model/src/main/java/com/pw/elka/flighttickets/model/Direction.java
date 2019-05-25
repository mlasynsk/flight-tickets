package com.pw.elka.flighttickets.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Direction implements Serializable {
    private String from;
    private String to;

    @JsonIgnore
    transient private Set<Ticket> tickets;

    public Direction() {
        tickets = new HashSet<>();
    }

    public Direction(String from, String to) {
        this.from = from;
        this.to = to;
        tickets = new HashSet<>();
    }

    @JsonIgnore
    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    @JsonIgnore
    public Set<Ticket> getFreeTickets() {
        return tickets.stream().filter(Ticket::isFree).collect(Collectors.toSet());
    }

    @JsonIgnore
    public Set<Ticket> getBookedTickets() {
        return tickets.stream().filter(t -> !t.isFree()).collect(Collectors.toSet());
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
                ", tickets=" + tickets +
                '}';
    }
}
