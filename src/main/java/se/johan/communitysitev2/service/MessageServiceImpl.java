package se.johan.communitysitev2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.johan.communitysitev2.model.MessageView;
import se.johan.communitysitev2.persistence.dao.MessageDao;
import se.johan.communitysitev2.persistence.hbm.Message;
import se.johan.communitysitev2.translator.MessageTranslator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johan on 04-May-16.
 */
@Service("messageService")
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private MessageTranslator messageTranslator;

    // Gets messages from db and translates them to messageviews, because not to expose unnecessary data.
    public List<MessageView> getMessagesByUser(Long id) {
        List<Message> messages = messageDao.getMessagesByUser(id);

        if (messages != null) {
            List<MessageView> messageViews = new ArrayList<MessageView>();
            for (Message m : messages) {
                messageViews.add(messageTranslator.translateMessageToMessageView(m));
            }
            return messageViews;
        }
        return null;
    }
    // Receives a message from controller and tells DAO to "send" it.
    public void postMessage(Message message) {
        messageDao.postMessage(message);
    }
}
