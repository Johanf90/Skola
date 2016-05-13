package se.johan.communitysitev2.persistence.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import se.johan.communitysitev2.persistence.hbm.Friend;
import se.johan.communitysitev2.persistence.hbm.FriendRequest;
import se.johan.communitysitev2.persistence.hbm.User;

import java.util.List;

/**
 * Created by Johan on 10-May-16.
 */
@Repository("friendDao")
public class FriendDaoImpl extends AbstractDao<Long, Friend> implements FriendDao {

    // Checks if 2 users are friends or not.
    public Friend checkIfAreFriends(Long userId1, Long userId2) {
        Criteria criteria = createEntityCriteria();
        Criterion res1 = Restrictions.and(Restrictions.eq("user1.id", userId1), Restrictions.eq("user2.id", userId2));
        Criterion res2 = Restrictions.and(Restrictions.eq("user1.id", userId2), Restrictions.eq("user2.id", userId1));
        criteria.add(Restrictions.or(res1, res2));
        return (Friend) criteria.uniqueResult();
    }
    // Checks if there is a friend request between 2 users.
    public FriendRequest checkIfFriendRequestExists(Long userId1, Long userId2) {
        Criteria criteria = getSession().createCriteria(FriendRequest.class);
        Criterion res1 = Restrictions.and(Restrictions.eq("request_receiver.id", userId1), Restrictions.eq("request_sender.id", userId2));
        Criterion res2 = Restrictions.and(Restrictions.eq("request_receiver.id", userId2), Restrictions.eq("request_sender.id", userId1));
        criteria.add(Restrictions.or(res1, res2));
        return null;
    }

    // Save a friend request.
    public void createFriendRequest(FriendRequest friendRequest) {
        getSession().persist(friendRequest);
    }
    // Save a friendship.
    public void createFriend(Friend friend) {
        persist(friend);
    }

    // Deletes a friend request.
    // Has to check both the user1 and user2 column because the user can be stored in either one of them.
    public void deleteFriendRequest(Long user1, Long user2) {
       String hql = "delete FriendRequest where (request_receiver= :user1 and request_sender= :user2) or (request_receiver= :user2 and request_sender= :user1)";

        Query q = getSession().createQuery(hql);
        q.setLong("user1", user1);
        q.setLong("user2", user2);
        int result = q.executeUpdate();
    }

    // Returns a list of friends belonging to a specific user.
    public List<Friend> getFriendsById(Long id) {
        Criteria criteria = createEntityCriteria();
        Criterion crit1 = Restrictions.eq("user1.id", id);
        Criterion crit2 = Restrictions.eq("user2.id", id);
        criteria.add(Restrictions.or(crit1, crit2));
        return criteria.list();
    }

}
