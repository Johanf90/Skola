package se.johan.communitysitev2.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import se.johan.communitysitev2.persistence.hbm.User;

/**
 * Created by Johan on 07-May-16.
 */
@Component
public class SecurityManager {

    // Returns the authenticated user which was set in "CustomUserDetailsService"- class.
    // This is necessary e.g. when sending messages to get the user id to set the "userSender" in the message model.
    public User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ((UserPrincipal) principal).getUser();
        return user;
    }

}
