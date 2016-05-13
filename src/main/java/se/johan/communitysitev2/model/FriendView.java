package se.johan.communitysitev2.model;

/**
 * Created by Johan on 12-May-16.
 */

// This is a Friend model used to expose ONYLY the necessary data.
public class FriendView {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
