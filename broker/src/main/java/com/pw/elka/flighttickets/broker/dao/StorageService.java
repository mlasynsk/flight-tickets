package com.pw.elka.flighttickets.broker.dao;

import com.pw.elka.flighttickets.model.Distributor;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class StorageService {

    private Set<Distributor> distributors;

    public StorageService() {
        distributors = new HashSet<>();
    }

    public Set<Distributor> getAll() {
        return distributors;
    }

    public void add(Distributor distributor) {
        this.distributors.add(distributor);
    }

    public void remove(String name) {
        distributors.stream()
                .filter(d -> d.getName().equals(name))
                .forEach(d -> distributors.remove(d));
    }
}
