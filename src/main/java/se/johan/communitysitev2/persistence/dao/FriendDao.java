package se.johan.communitysitev2.persistence.dao;

import se.johan.communitysitev2.persistence.hbm.Friend;
import se.johan.communitysitev2.persistence.hbm.FriendRequest;
import se.johan.communitysitev2.persistence.hbm.User;

import java.util.List;

/**
 * Created by Johan on 10-May-16.
 */
public interface FriendDao {

    Friend checkIfAreFriends(Long userId1, Long userId2);
    FriendRequest checkIfFriendRequestExists(Long userId1, Long userId2);
    void createFriendRequest(FriendRequest friendRequest);
    void createFriend(Friend friend);
    void deleteFriendRequest(Long user1, Long user2);
    List<Friend> getFriendsById(Long id);
}
