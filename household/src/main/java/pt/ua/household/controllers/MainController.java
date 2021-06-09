package pt.ua.household.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pt.ua.household.entities.User;
import pt.ua.household.services.UserService;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Principal principal, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken)
            model.addAttribute("login", false);
        else {
            User user = this.userService.getUserById(Long.parseLong(principal.getName().split("_")[0]));
            model.addAttribute("username", user.getFirstName() + " " + user.getLastName());
            model.addAttribute("login", true);
        }
        return "pages/index";
    }

}
