package se.johan.communitysitev2.model;

/**
 * Created by Johan on 06-May-16.
 */

// This is a User model used to expose ONYLY the necessary data.
public class UserView {

    private Long id;
    private String firstName;
    private String lastName;
    private boolean isFriends = true;
    private boolean hasFriendRequest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isFriends() {
        return isFriends;
    }

    public void setFriends(boolean friends) {
        isFriends = friends;
    }

    public boolean isHasFriendRequest() {
        return hasFriendRequest;
    }

    public void setHasFriendRequest(boolean hasFriendRequest) {
        this.hasFriendRequest = hasFriendRequest;
    }
}
