package se.johan.communitysitev2.persistence.dao;

import se.johan.communitysitev2.persistence.hbm.Message;

import java.util.List;

/**
 * Created by Johan on 04-May-16.
 */
public interface MessageDao {

    List<Message> getMessagesByUser(Long id);
    void postMessage(Message message);
}
