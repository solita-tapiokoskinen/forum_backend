package com.example.forum_backend.Comment;

import com.example.forum_backend.Topic.Topic;

import java.util.Date;

public class CommentDto {

    private Long id;
    private Long owner;
    private String comment;
    private Date createdAt;
    private Date updatedAt;
    private long topicId;

    public CommentDto() {

    }
    public CommentDto(Long id, Long owner, String comment, Date createdAt, Date updatedAt, long topicId) {
        this.id = id;
        this.owner = owner;
        this.comment = comment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.topicId = topicId;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", owner=" + owner +
                ", comment='" + comment + '\'' +
                ", topic='" + topicId + '\'' +
                ", created at='" + createdAt + '\'' +
                ", updated at='" + updatedAt + '\'' +
                '}';
    }

}
