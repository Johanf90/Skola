package se.johan.communitysitev2.persistence.hbm;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Johan on 04-May-16.
 */
@Entity
@Table(name = "message")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_receiver")
    private User userReceiver;
    @ManyToOne
    @JoinColumn(name = "user_sender")
    private User userSender;
    @Column(name = "created_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date dateCreated;
    @Column(name = "content")
    private String text;
    @Column(name = "friend_request", columnDefinition = "boolean")
    private Boolean friendRequest = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserReceiver() {
        return userReceiver;
    }

    public void setUserReceiver(User userReceiver) {
        this.userReceiver = userReceiver;
    }

    public User getUserSender() {
        return userSender;
    }

    public void setUserSender(User userSender) {
        this.userSender = userSender;
    }

    public String getDateCreated() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dateCreated);
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean isFriendRequest() {
        return friendRequest;
    }

    public void setFriendRequest(Boolean friendRequest) {
        this.friendRequest = friendRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;

        Message message = (Message) o;

        if (!id.equals(message.id)) return false;
        if (!userReceiver.equals(message.userReceiver)) return false;
        if (!userSender.equals(message.userSender)) return false;
        if (!dateCreated.equals(message.dateCreated)) return false;
        if (!text.equals(message.text)) return false;
        return friendRequest.equals(message.friendRequest);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + userReceiver.hashCode();
        result = 31 * result + userSender.hashCode();
        result = 31 * result + dateCreated.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + friendRequest.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", userReceiver=" + userReceiver +
                ", userSender=" + userSender +
                ", dateCreated=" + dateCreated +
                ", text='" + text + '\'' +
                ", friendRequest=" + friendRequest +
                '}';
    }
}
