package com.example.forum_backend.Topic;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class TopicDto {

    private Long id;
    private String title;
    private Long owner_id;
    private String ownerName;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private int comments;

    public TopicDto(Long id, String ownerName, String title, Long owner_id, ZonedDateTime createdAt, ZonedDateTime updatedAt, int comments) {
        this.id = id;
        this.title = title;
        this.owner_id = owner_id;
        this.ownerName = ownerName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.comments = comments;
    }

    public TopicDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
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
    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
    @Override
    public String toString() {
        return "TopicDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", owner_id=" + owner_id +
                ", ownerName=" + ownerName +
                ", created at=" + createdAt +
                ", updated at=" + updatedAt +
                ", comments=" + comments +
                '}';
    }


}
