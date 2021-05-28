package pt.ua.household.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "pages/index";
    }

    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
    public String dashboard() {
        return "pages/dashboard";
    }
}
