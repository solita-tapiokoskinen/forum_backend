package com.example.forum_backend.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService{

    private TopicRepository topicRepository;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public TopicDto addTopic(TopicDto topicDto) {
        Topic topic = new Topic();
        topic.setOwner(topicDto.getOwner_id());
        topic.setTitle(topicDto.getTitle());

        Topic newTopic = topicRepository.save(topic);

        TopicDto topicResponse = new TopicDto();
        topicResponse.setId(newTopic.getId());
        topicResponse.setOwner_id(newTopic.getOwner());
        topicResponse.setTitle(newTopic.getTitle());

        return topicResponse;

    }
}
