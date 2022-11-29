package com.example.forum_backend.Topic;

import com.example.forum_backend.Exceptions.TopicNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        topic.setCreatedAt(new Date());
        topic.setUpdatedAt(new Date());

        Topic newTopic = topicRepository.save(topic);

        return mapToDto(newTopic);

    }

    @Override
    public TopicResponse getAllTopics(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Topic> topics = topicRepository.findAll(pageable);
        List<Topic> listOfTopics = topics.getContent();
        List<TopicDto> content = listOfTopics.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        TopicResponse topicResponse = new TopicResponse();
        topicResponse.setContent(content);
        topicResponse.setPageNo(topics.getNumber());
        topicResponse.setPageSize(topics.getSize());
        topicResponse.setTotalElements(topics.getTotalElements());
        topicResponse.setTotalPages(topics.getTotalPages());
        topicResponse.setLast(topics.isLast());

        return topicResponse;
    }

    @Override
    public TopicDto getTopicById(Long id) {
        Topic topic = topicRepository.findById(id).orElseThrow(()-> new TopicNotFoundException("Topic could not be found"));
        return mapToDto(topic);
    }

    @Override
    public TopicDto updateTopic(TopicDto topicDto, long id) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new TopicNotFoundException("Topic not found"));

        topic.setTitle(topicDto.getTitle());
        topic.setUpdatedAt(new Date());

        Topic updatedTopic = topicRepository.save(topic);
        return mapToDto(updatedTopic);
    }

    @Override
    public void deleteTopic(long id) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new TopicNotFoundException("Topic not found"));
        topicRepository.delete(topic);
    }

    private TopicDto mapToDto(Topic topic) {
        TopicDto topicDto = new TopicDto();
        topicDto.setId(topic.getId());
        topicDto.setOwner_id(topic.getOwner());
        topicDto.setTitle(topic.getTitle());
        topicDto.setCreatedAt(topic.getCreatedAt());
        topicDto.setUpdatedAt(topic.getUpdatedAt());

        return topicDto;
    }

    private Topic mapToEntity(TopicDto topicDto) {
        Topic topic = new Topic();
        topic.setOwner(topicDto.getOwner_id());
        topic.setTitle(topicDto.getTitle());
        topic.setCreatedAt(topicDto.getCreatedAt());
        topic.setUpdatedAt(topicDto.getUpdatedAt());

        return topic;
    }

}
