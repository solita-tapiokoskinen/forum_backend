package com.example.forum_backend.Topic;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name="topics")
class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String created_at;
    @Column(nullable = false)
    private String updated_at;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Long owner_id;

    public Topic(String created_at, String updated_at, String title, Long owner_id) {
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.title = title;
        this.owner_id = owner_id;
    }

    public Topic(Long id, String created_at, String updated_at, String title, Long owner_id) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.title = title;
        this.owner_id = owner_id;
    }

    public Topic() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        return owner_id;
    }

    public void setOwner(Long owner_id) {
        this.owner_id = owner_id;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", title='" + title + '\'' +
                ", owner='" + owner_id + '\'' +
                '}';
    }
}
