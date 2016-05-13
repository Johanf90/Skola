package se.johan.communitysitev2.persistence.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import se.johan.communitysitev2.persistence.hbm.User;

import java.util.List;

/**
 * Created by Johan on 16-Apr-16.
 */
@Repository("userDao")
public class UserDAOImpl extends AbstractDao<Long, User> implements UserDAO {

    // Returns a user identified by username (email in this application).
    public User findByUsername(String username) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("email", username));
        return (User) criteria.uniqueResult();
    }
    // Saves a user in db.
    public void saveUser(User user) {
        persist(user);
    }
    // Returns a user identified by ID.
    public User getUser(Long id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", id));
        return (User) criteria.uniqueResult();
    }

}
