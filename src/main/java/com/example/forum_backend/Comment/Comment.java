package com.example.forum_backend.Comment;

import javax.persistence.*;
import com.example.forum_backend.Topic.Topic;
import com.example.forum_backend.UserEntity.UserEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="owner")
    private UserEntity owner;
    @Column(nullable = false)
    private String comment;
    @Column(nullable = false)
    @CreationTimestamp
    private ZonedDateTime createdAt;
    @Column(nullable = false)
    @UpdateTimestamp
    private ZonedDateTime updatedAt;

    public Comment() {

    }
    public Comment(Long id, UserEntity owner, String comment, Topic topic, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.owner = owner;
        this.comment = comment;
        this.topic = topic;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", owner=" + owner +
                ", comment='" + comment + '\'' +
                ", topic='" + topic + '\'' +
                ", created='" + createdAt + '\'' +
                ", updated='" + updatedAt + '\'' +
                '}';
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;
    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

}
