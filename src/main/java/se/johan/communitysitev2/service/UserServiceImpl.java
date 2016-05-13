package se.johan.communitysitev2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.johan.communitysitev2.model.UserView;
import se.johan.communitysitev2.persistence.dao.UserDAO;
import se.johan.communitysitev2.persistence.hbm.User;
import se.johan.communitysitev2.security.SecurityManager;
import se.johan.communitysitev2.translator.UserTranslator;

/**
 * Created by Johan on 16-Apr-16.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserTranslator userTranslator;
    @Autowired
    private SecurityManager securityManager;
    @Autowired
    private FriendService friendService;

    // Retrieves a username and tells DAO to find it.
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    // Tells DAO to save a specific user.
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    // Gets the authenticated user and translates it to userview.
    public UserView getAuthUser() {
        User user = securityManager.getAuthenticatedUser();
        return userTranslator.translateUserToUserView(user);
    }

    // When requesting a user it has to check if they are friends to know if the "Add friend" button should be visible or not.
    public UserView getUser(Long id) {
        User user = userDAO.getUser(id);
        User user2 = securityManager.getAuthenticatedUser();

        boolean areFriends = true;
        if (!user.getId().equals(user2.getId())) {
            areFriends = friendService.checkIfAreFriendsOrIfFriendRequestExists(user.getId(), user2.getId());
        }

        UserView userView = userTranslator.translateUserToUserView(user);
        userView.setFriends(areFriends);
        return userView;
    }


}
