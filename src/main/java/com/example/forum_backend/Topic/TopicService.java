package com.example.forum_backend.Topic;

import java.util.List;

public interface TopicService {

    TopicDto addTopic(TopicDto topicDto);

    TopicResponse getAllTopics(int pageNo, int pageSize);

    TopicDto getTopicById(Long id);

    TopicDto updateTopic(TopicDto topicDto, long id);

    void deleteTopic(long id);

}
