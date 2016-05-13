package se.johan.communitysitev2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.johan.communitysitev2.persistence.hbm.User;
import se.johan.communitysitev2.security.UserPrincipal;

import java.util.ArrayList;

/**
 * Created by Johan on 10-Feb-16.
 */

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    // Implementation of a custom userdetails to set the UserPrincipal to hold att instance of the autheticated user.
    // Thanks to that you can always get access to the user object (see SecurityManager).
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username: " +username);
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Username not found.");
        }

        return new UserPrincipal(user.getEmail(), user.getPassword(),
                new ArrayList<GrantedAuthority>(), user);
    }

}
