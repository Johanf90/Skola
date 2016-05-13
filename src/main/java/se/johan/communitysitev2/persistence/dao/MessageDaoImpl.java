package se.johan.communitysitev2.persistence.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import se.johan.communitysitev2.persistence.hbm.Message;

import java.util.List;

/**
 * Created by Johan on 04-May-16.
 */
@Repository("messageDao")
public class MessageDaoImpl extends AbstractDao<Long, Message> implements MessageDao {

    // Returns a list of messages belonging to a user with ID.
    public List<Message> getMessagesByUser(Long id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("userReceiver.id", id));
        criteria.addOrder(Order.desc("dateCreated"));
        return (List<Message>) criteria.list();
    }

    // Saves a message.
    public void postMessage(Message message) {
        persist(message);
    }
}
