package se.johan.communitysitev2.persistence.hbm;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Johan on 10-May-16.
 */
@Entity
@Table(name = "friends")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_1")
    private User user1;
    @ManyToOne
    @JoinColumn(name = "user_2")
    private User user2;
    @Column(name = "friends_since", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date friendsSince;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public Date getFriendsSince() {
        return friendsSince;
    }

    public void setFriendsSince(Date friendsSince) {
        this.friendsSince = friendsSince;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Friend)) return false;

        Friend friend = (Friend) o;

        if (!id.equals(friend.id)) return false;
        if (!user1.equals(friend.user1)) return false;
        if (!user2.equals(friend.user2)) return false;
        return friendsSince.equals(friend.friendsSince);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + user1.hashCode();
        result = 31 * result + user2.hashCode();
        result = 31 * result + friendsSince.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", user1=" + user1 +
                ", user2=" + user2 +
                ", friendsSince=" + friendsSince +
                '}';
    }
}
