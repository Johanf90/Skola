package se.johan.communitysitev2.persistence.dao;

import se.johan.communitysitev2.persistence.hbm.User;

/**
 * Created by Johan on 16-Apr-16.
 */
public interface UserDAO {

    User findByUsername(String username);
    void saveUser(User user);
    User getUser(Long id);
}
