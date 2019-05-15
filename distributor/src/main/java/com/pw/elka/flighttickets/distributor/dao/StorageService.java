package com.pw.elka.flighttickets.distributor.dao;

import com.pw.elka.flighttickets.model.Distributor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StorageService {

    private List<Distributor> distributors;

    public StorageService() {
        distributors = new ArrayList<>();
    }

    public List<Distributor> getAll() {
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
