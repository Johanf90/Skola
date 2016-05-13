package se.johan.communitysitev2.translator;

import org.springframework.stereotype.Component;
import se.johan.communitysitev2.model.MessageView;
import se.johan.communitysitev2.persistence.hbm.Message;

/**
 * Created by Johan on 05-May-16.
 */
@Component
public class MessageTranslator {

    // Translates a Message to message view with only the necessary information.
    public MessageView translateMessageToMessageView(Message message) {
        MessageView messageView = new MessageView();
        messageView.setUserID(message.getUserSender().getId());
        messageView.setUserSender(message.getUserSender().getFirstName() + " "
        + message.getUserSender().getLastName());
        messageView.setDateCreated(message.getDateCreated());
        messageView.setContent(message.getText());
        messageView.setFriendRequest(message.isFriendRequest());
        return messageView;
    }

}
