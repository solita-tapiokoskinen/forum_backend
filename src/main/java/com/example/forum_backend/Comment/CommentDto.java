package com.example.forum_backend.Comment;

import java.time.ZonedDateTime;

public class CommentDto {

    private Long id;
    private Long owner;
    private String ownerName;
    private String comment;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private long topicId;

    public CommentDto() {

    }
    public CommentDto(Long id, Long owner, String comment, ZonedDateTime createdAt, ZonedDateTime updatedAt, long topicId, String ownerName) {
        this.id = id;
        this.owner = owner;
        this.ownerName = ownerName;
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
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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
                ", ownerName=" + ownerName +
                ", comment='" + comment + '\'' +
                ", topic='" + topicId + '\'' +
                ", created at='" + createdAt + '\'' +
                ", updated at='" + updatedAt + '\'' +
                '}';
    }

}
