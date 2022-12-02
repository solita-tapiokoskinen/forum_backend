package com.example.forum_backend.Topic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TopicDto {

    private Long id;
    private String title;
    private Long owner_id;
    private Date createdAt;
    private Date updatedAt;


    private List<Long> comments = new ArrayList<>();

    public TopicDto(Long id, String title, Long owner_id, Date createdAt, Date updatedAt, List<Long> comments) {
        this.id = id;
        this.title = title;
        this.owner_id = owner_id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.comments = comments;
    }

    public TopicDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

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
    public List<Long> getComments() {
        return comments;
    }

    public void setComments(List<Long> comments) {
        this.comments = comments;
    }
    @Override
    public String toString() {
        return "TopicDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", owner_id=" + owner_id +
                ", created at=" + createdAt +
                ", updated at=" + updatedAt +
                ", comments=" + comments +
                '}';
    }


}
