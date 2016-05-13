package se.johan.communitysitev2.service;

import se.johan.communitysitev2.model.MessageView;
import se.johan.communitysitev2.persistence.hbm.Message;

import java.util.List;

/**
 * Created by Johan on 04-May-16.
 */
public interface MessageService {

    List<MessageView> getMessagesByUser(Long id);
    void postMessage(Message message);
}
