package se.johan.communitysitev2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.johan.communitysitev2.model.UserView;
import se.johan.communitysitev2.service.UserService;

/**
 * Created by Johan on 06-May-16.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Returns the user authenticated.
    @RequestMapping(method = RequestMethod.GET)
    public UserView getAuthUser() {
        return userService.getAuthUser();
    }

    // Returns a specific user by ID.
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserView getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

}
