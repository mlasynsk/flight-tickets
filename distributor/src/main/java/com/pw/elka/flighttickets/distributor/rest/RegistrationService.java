package com.pw.elka.flighttickets.distributor.rest;

import com.pw.elka.flighttickets.distributor.dao.StorageService;
import com.pw.elka.flighttickets.model.Distributor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RegistrationService {

    @Value("${broker.uri}")
    private String registrationUri;

    @Value("${broker.uri.unregister}")
    private String unRegistrationUri;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StorageService storageService;

    @PostConstruct
    public void registerToBroker() {

        Runnable runnable = () -> {
            while (true) {
                try {
                    Distributor me = storageService.getMe();
                    ResponseEntity<Distributor[]> otherResponseEntity = restTemplate.postForEntity(registrationUri, me, Distributor[].class);

                    List<Distributor> other = Stream
                            .of(otherResponseEntity.getBody())
                            .filter(d -> !d.equals(me))
                            .collect(Collectors.toList());

                    storageService.setOthers(other);
                    Thread.sleep(20000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    @PreDestroy
    public void unRegister(){
        restTemplate.postForEntity(unRegistrationUri,storageService.getMe(),Object.class);
    }
}
