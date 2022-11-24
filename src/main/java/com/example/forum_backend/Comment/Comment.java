package com.example.forum_backend.Comment;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long owner;


    @Column(nullable = false)
    private Long topicId;

    @Column(nullable = false)
    private String comment;

    public Comment() {

    }
    public Comment(Long id, Long owner, Long topicId, String comment) {
        this.id = id;
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
                ", owner=" + owner +
                ", topic_id=" + topicId +
                ", comment='" + comment + '\'' +
                '}';
    }


}
