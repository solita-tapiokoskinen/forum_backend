package com.example.forum_backend.Comment;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String created_at;

    @Column(nullable = false)
    private String updated_at;

    @Column(nullable = false)
    private Long owner;


    @Column(nullable = false)
    private Long topicId;

    @Column(nullable = false)
    private String comment;

    public Comment() {

    }
    public Comment(Long id, String created_at, String updated_at, Long owner, Long topicId, String comment) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.owner = owner;
        this.topicId = topicId;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }
    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topic_id) {
        this.topicId = topic_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", owner=" + owner +
                ", topic_id=" + topicId +
                ", comment='" + comment + '\'' +
                '}';
    }


}
