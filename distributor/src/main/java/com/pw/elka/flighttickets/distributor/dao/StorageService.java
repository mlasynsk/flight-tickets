package com.pw.elka.flighttickets.distributor.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pw.elka.flighttickets.model.Distributor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

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
        System.out.println(me);

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