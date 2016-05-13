package se.johan.communitysitev2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.johan.communitysitev2.model.FriendView;
import se.johan.communitysitev2.persistence.dao.FriendDao;
import se.johan.communitysitev2.persistence.hbm.Friend;
import se.johan.communitysitev2.persistence.hbm.FriendRequest;
import se.johan.communitysitev2.persistence.hbm.Message;
import se.johan.communitysitev2.persistence.hbm.User;
import se.johan.communitysitev2.security.SecurityManager;
import se.johan.communitysitev2.translator.FriendTranslator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johan on 10-May-16.
 */
@Service("friendService")
@Transactional
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendDao friendDao;
    @Autowired
    private SecurityManager securityManager;
    @Autowired
    private MessageService messageService;
    @Autowired
    private FriendTranslator friendTranslator;

    // Checks if there is a friend request or if 2 users are friends.
    public boolean checkIfAreFriendsOrIfFriendRequestExists(Long userId1, Long userId2) {
        Friend friend = friendDao.checkIfAreFriends(userId1, userId2);
        if (friend != null) {
            return true;
        }
        FriendRequest friendRequest = friendDao.checkIfFriendRequestExists(userId1, userId2);
        if (friendRequest != null) {
            return true;
        }
        return false;
    }

    // Creates a friend request and "sends" a message to the request receiver.
    public void makeFriendRequest(Long requestReceiverId) {
        User requestReceiver = new User();
        requestReceiver.setId(requestReceiverId);
        User requestSender = securityManager.getAuthenticatedUser();

        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setRequestReceiver(requestReceiver);
        friendRequest.setRequestSender(requestSender);
        friendDao.createFriendRequest(friendRequest);

        Message message = new Message();
        message.setUserSender(requestSender);
        message.setUserReceiver(requestReceiver);
        message.setText(requestSender.getFirstName() + " " + requestSender.getLastName() +
        " wants to be friend with you :-)");
        message.setFriendRequest(true);
        messageService.postMessage(message);

    }

    // Accpets a friend request and creates a Friend and deletes the friend reqeust.
    public void acceptFriendRequest(Long id) {
        User user1 = new User();
        user1.setId(id);

        User user2 = securityManager.getAuthenticatedUser();

        Friend friend = new Friend();
        friend.setUser1(user1);
        friend.setUser2(user2);

        friendDao.createFriend(friend);
        friendDao.deleteFriendRequest(user1.getId(), user2.getId());
    }

    // Returns a list of friends belonging to user identified by ID.
    public List<FriendView> getFriendsById(Long id) {
        List<Friend> list = friendDao.getFriendsById(id);
        List<FriendView> friendViews = new ArrayList<FriendView>();

        for (Friend f : list) {
            friendViews.add(friendTranslator.translateFriendToFriendView(f, id));
        }

        return friendViews;
    }

}
