package com.pw.elka.flighttickets.distributor.rest;

import com.pw.elka.flighttickets.distributor.dao.StorageService;
import com.pw.elka.flighttickets.model.Distributor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RegistrationService {

    @Value("${broker.uri}")
    private String registrationUri;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StorageService storageService;

    @PostConstruct
    public void registerToBroker() {

        Runnable runnable = () -> {
            while (true) {
                Distributor me = storageService.getMe();
                ResponseEntity<Distributor[]> otherResponseEntity = restTemplate.postForEntity(registrationUri, me, Distributor[].class);

                List<Distributor> other = Stream
                        .of(otherResponseEntity.getBody())
                        .filter(d -> !d.equals(me))
                        .collect(Collectors.toList());

                storageService.setOthers(other);
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
