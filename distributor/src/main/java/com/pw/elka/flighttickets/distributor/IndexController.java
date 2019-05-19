package com.pw.elka.flighttickets.distributor;

import com.pw.elka.flighttickets.distributor.dao.StorageService;
import com.pw.elka.flighttickets.model.Distributor;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Api("/")
@Controller
public class IndexController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/")
    public String homePage(Model model) {
        Distributor me = storageService.getMe();
        model.addAttribute("me", me);
        model.addAttribute("other", storageService.getOthers());
        return "index";
    }

}
