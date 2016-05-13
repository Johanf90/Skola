package se.johan.communitysitev2.model;

/**
 * Created by Johan on 05-May-16.
 */

// This is a Message model used to expose ONYLY the necessary data.
public class MessageView {

    private Long userID;
    private String userSender;
    private String dateCreated;
    private String content;
    private boolean friendRequest;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserSender() {
        return userSender;
    }

    public void setUserSender(String userSender) {
        this.userSender = userSender;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFriendRequest() {
        return friendRequest;
    }

    public void setFriendRequest(boolean friendRequest) {
        this.friendRequest = friendRequest;
    }
}
