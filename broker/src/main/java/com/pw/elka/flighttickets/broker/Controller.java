package com.pw.elka.flighttickets.broker;

import com.pw.elka.flighttickets.broker.dao.StorageService;
import com.pw.elka.flighttickets.model.Distributor;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("/broker")
@RestController
@RequestMapping("/broker")
public class Controller {

    @Autowired
    private StorageService storageService;

    @GetMapping("/all")
    @ResponseBody
    public List<Distributor> greet() {
        return storageService.getAll();
    }

    @PostMapping(value = "/register", consumes = "application/json")
    public void register(@RequestBody Distributor distributor) {
        storageService.add(distributor);
    }

    @PostMapping(value = "/unregister", consumes = "application/json")
    public void unregister(@RequestBody Distributor distributor) {
        storageService.remove(distributor.getName());
    }
}
