package com.pw.elka.flighttickets.distributor.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pw.elka.flighttickets.model.Direction;
import com.pw.elka.flighttickets.model.Distributor;
import com.pw.elka.flighttickets.model.Ticket;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class StorageService {

    final static Logger logger = Logger.getLogger(StorageService.class);

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
            Set<Ticket> newTickets = IntStream.range(1, 100000).mapToObj(Ticket::new).collect(Collectors.toSet());
            direction.setTickets(newTickets);
        }
//        System.out.println(me);

    }

    public synchronized boolean book(Direction direction) {
        Optional<Direction> optional = me.getDirections()
                .stream()
                .filter(d -> d.getFrom().equals(direction.getFrom()))
                .filter(d -> d.getTo().equals(direction.getTo()))
                .findAny();
        if (optional.isPresent()) {
            Set<Ticket> free = optional.get().getFreeTickets();
            if (!free.isEmpty()) {
                Ticket ticketToBuy = free.iterator().next();
                logger.info("Sold ticked number: " + ticketToBuy.getNumber());
                ticketToBuy.setFree(false);
                return true;
            } else {
                System.out.println("No tickets left for: " + direction);
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

    public int findPortByName(String distributor) {
        return others.stream()
                .filter(d -> distributor.equals(d.getName()))
                .findAny()
                .orElseThrow(RuntimeException::new)
                .getPort();
    }
}
