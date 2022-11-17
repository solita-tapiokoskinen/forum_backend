package com.example.forum_backend.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Autowired
    public List<Topic> getTopic() {
        return topicRepository.findAll();
    }

    public void addNewTopic(Topic topic) {
        topicRepository.save(topic);
    }
}
