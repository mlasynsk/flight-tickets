package com.pw.elka.flighttickets.model;

public class Ticket {
    private Integer number;
    private boolean isFree = true;

    public Ticket(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "number=" + number +
                ", isFree=" + isFree +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;

        Ticket ticket = (Ticket) o;

        return getNumber().equals(ticket.getNumber());

    }

    @Override
    public int hashCode() {
        return getNumber().hashCode();
    }
}
