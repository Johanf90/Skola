package se.johan.communitysitev2.service;

import se.johan.communitysitev2.model.FriendView;

import java.util.List;

/**
 * Created by Johan on 10-May-16.
 */
public interface FriendService {

    boolean checkIfAreFriendsOrIfFriendRequestExists(Long userId1, Long userId2);
    void makeFriendRequest(Long requestReceiverId);
    void acceptFriendRequest(Long id);
    List<FriendView> getFriendsById(Long id);

}
