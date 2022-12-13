package com.example.forum_backend.Service;

import com.example.forum_backend.Topic.*;
import com.example.forum_backend.UserEntity.UserEntity;
import com.example.forum_backend.UserEntity.UserRepository;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TopicServiceTest {

    @Mock
    private TopicRepository topicRepository;

    @Mock
    private UserRepository userRepository;
    @Mock
    Authentication authentication = Mockito.mock(Authentication.class);
    @Mock
    SecurityContext securityContext = Mockito.mock(SecurityContext.class);

    @InjectMocks
    private TopicServiceImpl topicService;

    @Test
    public void TopicService_CreateTopic_ReturnsTopicDto() {

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("testuser");

        Topic topic = new Topic();
        topic.setTitle("Unit test");
        topic.setOwner(user);
        topic.setCreatedAt(ZonedDateTime.now());
        topic.setUpdatedAt(ZonedDateTime.now());

        TopicDto topicDto = new TopicDto();
        topicDto.setTitle("Unit test");
        topicDto.setOwner_id(1L);
        topicDto.setCreatedAt(ZonedDateTime.now());
        topicDto.setUpdatedAt(ZonedDateTime.now());

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn(user.getUsername());

        when(topicRepository.save(Mockito.any(Topic.class))).thenReturn(topic);
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        TopicDto savedTopic = topicService.addTopic(topicDto);
        Assertions.assertThat(savedTopic).isNotNull();

    }

    @Test
    public void TopicService_GetAllTopic_ReturnsResponseDto() {
        Page<Topic> topics = Mockito.mock(Page.class);

        when(topicRepository.findAll(Mockito.any(Pageable.class))).thenReturn(topics);

        TopicResponse saveTopic = topicService.getAllTopics(1,10);

        Assertions.assertThat(saveTopic).isNotNull();
    }

    @Test
    public void TopicService_GetTopicById_ReturnsTopicDto() {
        UserEntity user = new UserEntity();
        user.setId(1L);

        Topic topic = new Topic();
        topic.setTitle("Unit test");
        topic.setOwner(user);
        topic.setCreatedAt(ZonedDateTime.now());
        topic.setUpdatedAt(ZonedDateTime.now());

        TopicDto topicDto = new TopicDto();
        topicDto.setTitle("Unit test");
        topicDto.setOwner_id(1L);
        topicDto.setCreatedAt(ZonedDateTime.now());
        topicDto.setUpdatedAt(ZonedDateTime.now());

        when(topicRepository.findById(1L)).thenReturn(Optional.ofNullable(topic));

        TopicDto savedTopic = topicService.getTopicById(1L);

        Assertions.assertThat(savedTopic).isNotNull();
    }

    @Test
    public void TopicService_UpdateTopicById_ReturnsTopicDto() {
        UserEntity user = new UserEntity();
        user.setId(1L);

        Topic topic = new Topic();
        topic.setTitle("Unit test");
        topic.setOwner(user);
        topic.setCreatedAt(ZonedDateTime.now());
        topic.setUpdatedAt(ZonedDateTime.now());

        TopicDto topicDto = new TopicDto();
        topicDto.setTitle("Unit test");
        topicDto.setOwner_id(1L);
        topicDto.setCreatedAt(ZonedDateTime.now());
        topicDto.setUpdatedAt(ZonedDateTime.now());

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn(user.getUsername());

        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        when(topicRepository.findById(1L)).thenReturn(Optional.ofNullable(topic));
        when(topicRepository.save(Mockito.any(Topic.class))).thenReturn(topic);

        TopicDto savedTopic = topicService.updateTopic(topicDto, 1L);

        Assertions.assertThat(savedTopic).isNotNull();
    }

    @Test
    public void TopicService_DeleteTopicById_ReturnsTopicDto() {
        Topic topic = new Topic();
        topic.setTitle("Unit test");
        topic.setCreatedAt(ZonedDateTime.now());
        topic.setUpdatedAt(ZonedDateTime.now());

        when(topicRepository.findById(1L)).thenReturn(Optional.ofNullable(topic));

        assertAll(() -> topicService.deleteTopic(1L));
    }

}
