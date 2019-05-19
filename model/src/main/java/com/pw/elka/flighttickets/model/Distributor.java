package com.pw.elka.flighttickets.model;

import java.util.List;

public class Distributor {
    private String name;
    private List<Direction> directions;

    public Distributor() {
    }

    public Distributor(String name, List<Direction> directions) {
        this.name = name;
        this.directions = directions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public void setDirections(List<Direction> directions) {
        this.directions = directions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Distributor)) return false;

        Distributor that = (Distributor) o;

        return this.name.equals(that.getName());
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Distributor{" +
                "name='" + name + '\'' +
                ", directions=" + directions +
                '}';
    }
}
