package com.pw.elka.flighttickets.distributor;

import com.pw.elka.flighttickets.distributor.akka.BookingService;
import com.pw.elka.flighttickets.distributor.dao.StorageService;
import com.pw.elka.flighttickets.model.Direction;
import com.pw.elka.flighttickets.model.Distributor;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Api("/")
@Controller
public class IndexController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/")
    public String homePage(Model model) {
        Distributor me = storageService.getMe();
        model.addAttribute("me", me);
        model.addAttribute("bookingService", bookingService);
        model.addAttribute("other", storageService.getOthers());
        return "index";
    }

    @PostMapping("/book")
    public String ask(Model model,
                      @ModelAttribute("from") String from,
                      @ModelAttribute("to") String to) {
        bookingService.book(new Direction(from,to));

        return this.homePage(model);
    }

}
