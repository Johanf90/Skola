package se.johan.communitysitev2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.johan.communitysitev2.model.MessageView;
import se.johan.communitysitev2.persistence.hbm.Message;
import se.johan.communitysitev2.persistence.hbm.User;
import se.johan.communitysitev2.security.SecurityManager;
import se.johan.communitysitev2.service.MessageService;

import java.util.List;

/**
 * Created by Johan on 04-May-16.
 */
@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private SecurityManager securityManager;

    // Returns all messages belonging to a user.
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<MessageView> getMessagesByUser(@PathVariable Long id) {
        System.out.println("MEssages/id invoked!");
        if (id == null) {
            return null;
        }
        List<MessageView> MessageViews = messageService.getMessagesByUser(id);

        System.out.println("Returning messages");
        return MessageViews;
    }

    // Posts a message to a user identified by ID.
    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes="application/json; charset=UTF-8")
    public void postMessage(@PathVariable Long id, @RequestBody Message message) {
        System.out.println("SENDING MESSAGE");
        if (message.getText() != null) {
            User userRec = new User();
            userRec.setId(id);
            User userSender = securityManager.getAuthenticatedUser();
            message.setUserReceiver(userRec);
            message.setUserSender(userSender);
            messageService.postMessage(message);
        }
    }



}
