package com.pw.elka.flighttickets.distributor.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pw.elka.flighttickets.model.Direction;
import com.pw.elka.flighttickets.model.Distributor;
import com.pw.elka.flighttickets.model.Ticket;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class StorageService {

    private Distributor me;

    private List<Distributor> others;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() throws IOException {

        String initFile = System.getProperty("init.file");
        if (initFile == null || initFile.isEmpty()) throw new RuntimeException("ConfigFile not specified!");

        String json = IOUtils.toString(this.getClass().getResourceAsStream("/" + initFile), "UTF-8");
        me = objectMapper.readValue(json, Distributor.class);


        for (Direction direction : me.getDirections()) {
            Set<Ticket> newTickets = IntStream.range(1, 10).mapToObj(Ticket::new).collect(Collectors.toSet());
            direction.setTickets(newTickets);
        }
        System.out.println(me);

    }

    public boolean book(Direction direction) {
        Optional<Direction> optional = me.getDirections()
                .stream()
                .filter(d -> d.getFrom().equals(direction.getFrom()))
                .filter(d -> d.getTo().equals(direction.getTo()))
                .findAny();
        if (optional.isPresent()) {
            Set<Ticket> free = optional.get().getFreeTickets();
            if (!free.isEmpty()) {
                free.iterator().next().setFree(false);
                return true;
            }
            return false;
        } else return false;
    }


    public Distributor getMe() {
        return me;
    }

    public List<Distributor> getOthers() {
        return others;
    }

    public void setOthers(List<Distributor> others) {
        this.others = others;
    }
}
