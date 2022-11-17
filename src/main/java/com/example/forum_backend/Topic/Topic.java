package com.example.forum_backend.Topic;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name="topic")
class Topic {

    private @Id Long id;
    private String created_at;
    private String updated_at;
    private String title;
    private Long owner;

    public Topic(String created_at, String updated_at, String title, Long owner) {
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.title = title;
        this.owner = owner;
    }

    public Topic(Long id, String created_at, String updated_at, String title, Long owner) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.title = title;
        this.owner = owner;
    }

    public Topic() {

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", title='" + title + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
