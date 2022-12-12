package com.example.forum_backend.Topic;

import com.example.forum_backend.Exceptions.TopicNotFoundException;
import com.example.forum_backend.Exceptions.UnathorizedException;
import com.example.forum_backend.Exceptions.UserNotFoundException;
import com.example.forum_backend.UserEntity.UserEntity;
import com.example.forum_backend.UserEntity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService{

    private TopicRepository topicRepository;
    private UserRepository userRepository;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository, UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TopicDto addTopic(TopicDto topicDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserEntity user = userRepository.findByUsername(currentPrincipalName).orElseThrow(() -> new UserNotFoundException(("User not found")));
        Topic topic = new Topic();
        topic.setOwner(user);
        topic.setTitle(topicDto.getTitle());
        topic.setCreatedAt(LocalDateTime.now());
        topic.setUpdatedAt(LocalDateTime.now());

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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserEntity user = userRepository.findByUsername(currentPrincipalName).orElseThrow(() -> new UserNotFoundException(("User not found")));

        if (Objects.equals(topic.getOwnerEntity().getId(), user.getId())){
            topic.setTitle(topicDto.getTitle());
            topic.setUpdatedAt(LocalDateTime.now());

            Topic updatedTopic = topicRepository.save(topic);
            return mapToDto(updatedTopic);
        }
        else {
            throw new UnathorizedException("User does not own this topic");
        }
    }

    @Override
    public void deleteTopic(long id) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new TopicNotFoundException("Topic not found"));
        topicRepository.delete(topic);
    }

    private TopicDto mapToDto(Topic topic) {
        TopicDto topicDto = new TopicDto();
        topicDto.setId(topic.getId());
        topicDto.setTitle(topic.getTitle());
        topicDto.setOwner_id(topic.getOwnerEntity().getId());
        topicDto.setOwnerName(topic.getOwnerEntity().getUsername());
        topicDto.setComments(topic.getComments().size());
        topicDto.setCreatedAt(topic.getCreatedAt());
        topicDto.setUpdatedAt(topic.getUpdatedAt());

        return topicDto;
    }

    private Topic mapToEntity(TopicDto topicDto) {
        Topic topic = new Topic();
        topic.setTitle(topicDto.getTitle());
        topic.setCreatedAt(topicDto.getCreatedAt());
        topic.setUpdatedAt(topicDto.getUpdatedAt());

        return topic;
    }

}
