package com.pw.elka.flighttickets.distributor;

import com.pw.elka.flighttickets.distributor.dao.StorageService;
import com.pw.elka.flighttickets.model.Distributor;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("/")
@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    private StorageService storageService;

    @GetMapping("/index")
    public String homePage(Model model) {
        model.addAttribute("person", "Michael");
        model.addAttribute("page", "main");
        return "index";
    }

    @GetMapping("/users")
    public String usersPage(Model model) {
        model.addAttribute("person", "Jack");
        model.addAttribute("page", "users");
        return "index";
    }

}
