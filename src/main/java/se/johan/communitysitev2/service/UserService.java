package se.johan.communitysitev2.service;

import se.johan.communitysitev2.model.UserView;
import se.johan.communitysitev2.persistence.hbm.User;

/**
 * Created by Johan on 16-Apr-16.
 */
public interface UserService {

    User findByUsername(String username);
    void saveUser(User user);
    UserView getAuthUser();
    UserView getUser(Long id);

}
