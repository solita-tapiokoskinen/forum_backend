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
    private String title;
    @Column(nullable = false)
    private Long owner_id;

    public Topic(Long id, String title, Long owner_id) {
        this.id = id;
        this.title = title;
        this.owner_id = owner_id;
    }

    public Topic() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                ", title='" + title + '\'' +
                ", owner='" + owner_id + '\'' +
                '}';
    }
}
