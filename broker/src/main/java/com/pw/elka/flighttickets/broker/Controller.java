package com.pw.elka.flighttickets.broker;

import com.pw.elka.flighttickets.broker.dao.StorageService;
import com.pw.elka.flighttickets.model.Distributor;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Api("/broker")
@RestController
@RequestMapping("/broker")
public class Controller {

    @Autowired
    private StorageService storageService;

    @GetMapping("/all")
    @ResponseBody
    public Set<Distributor> greet() {
        return storageService.getAll();
    }

    @PostMapping(value = "/register", consumes = "application/json")
    public Set<Distributor> register(@RequestBody Distributor distributor) {
        Set<Distributor> registered = storageService.getAll();
        storageService.add(distributor);
        return registered;
    }

    @PostMapping(value = "/unregister", consumes = "application/json")
    public void unregister(@RequestBody Distributor distributor) {
        storageService.remove(distributor.getName());
    }
}
