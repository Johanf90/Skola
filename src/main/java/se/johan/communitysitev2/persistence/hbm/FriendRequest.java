package se.johan.communitysitev2.persistence.hbm;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Johan on 10-May-16.
 */
@Entity
@Table(name = "friend_request")
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "request_receiver")
    private User requestReceiver;
    @ManyToOne
    @JoinColumn(name = "request_sender")
    private User requestSender;
    @Column(name = "request_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date requestDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getRequestReceiver() {
        return requestReceiver;
    }

    public void setRequestReceiver(User requestReceiver) {
        this.requestReceiver = requestReceiver;
    }

    public User getRequestSender() {
        return requestSender;
    }

    public void setRequestSender(User requestSender) {
        this.requestSender = requestSender;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
}
