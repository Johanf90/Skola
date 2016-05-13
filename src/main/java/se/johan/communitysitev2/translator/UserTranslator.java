package se.johan.communitysitev2.translator;

import org.springframework.stereotype.Component;
import se.johan.communitysitev2.model.UserView;
import se.johan.communitysitev2.persistence.hbm.User;

/**
 * Created by Johan on 06-May-16.
 */
@Component
public class UserTranslator {

    // Translates a User to user view with only the necessary information.
    public UserView translateUserToUserView(User user) {
        UserView userView = new UserView();
        userView.setId(user.getId());
        userView.setFirstName(user.getFirstName());
        userView.setLastName(user.getLastName());
        return userView;
    }
}
