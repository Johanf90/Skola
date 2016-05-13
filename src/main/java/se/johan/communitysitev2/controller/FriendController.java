package se.johan.communitysitev2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.johan.communitysitev2.model.FriendView;
import se.johan.communitysitev2.model.UserView;
import se.johan.communitysitev2.service.FriendService;

import java.util.List;

/**
 * Created by Johan on 10-May-16.
 */
@RestController
@RequestMapping(value = "/friends")
public class FriendController {

    @Autowired
    private FriendService friendService;

    // Adds a friend att gets the id to the friend in the URL.
    @RequestMapping(value = "/request/{id}", method = RequestMethod.GET)
    public ResponseEntity addFriend(@PathVariable Long id) {

        friendService.makeFriendRequest(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    // Accpets a friend request from a user with the ID in the URL.
    @RequestMapping(value = "/accept/{id}", method = RequestMethod.GET)
    public ResponseEntity acceptRequest(@PathVariable Long id) {
        friendService.acceptFriendRequest(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    // Returns all friend to user by ID in URL.
    @RequestMapping(value = "/by/{id}", method = RequestMethod.GET)
    public List<FriendView> getFriendsById(@PathVariable Long id) {
        return friendService.getFriendsById(id);

    }

}
