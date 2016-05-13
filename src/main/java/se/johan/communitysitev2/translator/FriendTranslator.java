package se.johan.communitysitev2.translator;

import org.springframework.stereotype.Component;
import se.johan.communitysitev2.model.FriendView;
import se.johan.communitysitev2.persistence.hbm.Friend;

/**
 * Created by Johan on 12-May-16.
 */
@Component
public class FriendTranslator {

    // Translates a Friend to friend view with only the necessary information.
    public FriendView translateFriendToFriendView(Friend friend, Long id) {
        FriendView friendView = new FriendView();

        if (!friend.getUser1().getId().equals(id)) {
            friendView.setId(friend.getUser1().getId());
            friendView.setName(friend.getUser1().getFirstName() + " " + friend.getUser1().getLastName());
        } else {
            friendView.setId(friend.getUser2().getId());
            friendView.setName(friend.getUser2().getFirstName() + " " + friend.getUser2().getLastName());
        }

        return friendView;
    }

}
