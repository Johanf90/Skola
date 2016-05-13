package se.johan.communitysitev2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import se.johan.communitysitev2.persistence.hbm.User;
import se.johan.communitysitev2.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Johan on 16-Apr-16.
 */
@Controller
public class ApplicationController {

    @Autowired
    private UserService userService;

    // Returns the login page.
    @RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
    public String getLogin() {
        return "login";
    }

    // Handles the user sign up.
    @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes="application/json; charset=UTF-8")
    public String signup(@RequestBody User user) {

        System.out.println(user.getEmail());
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getPassword());

        userService.saveUser(user);

        return "home";
    }

    // Returns the home.html
    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String test() {
        System.out.println("RETURN HOME");
        return "home";
    }
    // Returns error page
    @RequestMapping(value = {"/error"}, method = RequestMethod.GET)
    public String test2() {
        return "error";
    }

    // Handles logut request.
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout";
    }
}
