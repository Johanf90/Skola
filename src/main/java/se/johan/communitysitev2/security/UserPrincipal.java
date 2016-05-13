package se.johan.communitysitev2.security;

import org.springframework.security.core.GrantedAuthority;
import se.johan.communitysitev2.persistence.hbm.User;

import java.util.Collection;

/**
 * Created by Johan on 14-Feb-16.
 */
public class UserPrincipal extends org.springframework.security.core.userdetails.User {

    private User user;

    public UserPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities, User user) {
        super(username, password, authorities);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
