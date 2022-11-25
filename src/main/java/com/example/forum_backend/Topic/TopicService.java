package com.example.forum_backend.Topic;

import java.util.List;

public interface TopicService {

    TopicDto addTopic(TopicDto topicDto);

    List<TopicDto> getAllTopics();

    TopicDto getTopicById(Long id);

    TopicDto updateTopic(TopicDto topicDto, long id);
}
