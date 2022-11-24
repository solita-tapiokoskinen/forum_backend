package com.example.forum_backend.Topic;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TopicDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Long owner_id;

    public TopicDto(Long id, String title, Long owner_id) {
        this.id = id;
        this.title = title;
        this.owner_id = owner_id;
    }

    public TopicDto(){}

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

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }
    @Override
    public String toString() {
        return "TopicDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", owner_id=" + owner_id +
                '}';
    }


}
