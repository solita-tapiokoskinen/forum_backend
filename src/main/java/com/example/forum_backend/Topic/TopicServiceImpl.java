package com.example.forum_backend.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<TopicDto> getAllTopics() {
        List<Topic> topic = topicRepository.findAll();
        return topic.stream().map(t -> mapToDto(t)).collect(Collectors.toList());
    }

    private TopicDto mapToDto(Topic topic) {
        TopicDto topicDto = new TopicDto();
        topicDto.setId(topic.getId());
        topicDto.setOwner_id(topic.getOwner());
        topicDto.setTitle(topic.getTitle());

        return topicDto;
    }

    private Topic mapToEntity(TopicDto topicDto) {
        Topic topic = new Topic();
        topic.setOwner(topicDto.getOwner_id());
        topic.setTitle(topicDto.getTitle());
        return topic;
    }

}
