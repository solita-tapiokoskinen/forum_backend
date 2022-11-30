package com.example.forum_backend.Service;

import com.example.forum_backend.Topic.*;
import org.junit.Test;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class TopicServiceTest {

    @Mock
    private TopicRepository topicRepository;

    @InjectMocks
    private TopicServiceImpl topicService;

    @Test
    public void TopicService_CreateTopic_ReturnsTopicDto() {
        Topic topic = new Topic();
        topic.setTitle("Unit test");
        topic.setOwner(1L);
        topic.setCreatedAt(new Date());
        topic.setUpdatedAt(new Date());

        TopicDto topicDto = new TopicDto();
        topicDto.setTitle("Unit test");
        topicDto.setOwner_id(1L);
        topicDto.setCreatedAt(new Date());
        topicDto.setUpdatedAt(new Date());

        when(topicRepository.save(Mockito.any(Topic.class))).thenReturn(topic);

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
        Topic topic = new Topic();
        topic.setTitle("Unit test");
        topic.setOwner(1L);
        topic.setCreatedAt(new Date());
        topic.setUpdatedAt(new Date());

        when(topicRepository.findById(1L)).thenReturn(Optional.ofNullable(topic));

        TopicDto savedTopic = topicService.getTopicById(1L);

        Assertions.assertThat(savedTopic).isNotNull();
    }

    @Test
    public void TopicService_UpdateTopicById_ReturnsTopicDto() {
        Topic topic = new Topic();
        topic.setTitle("Unit test");
        topic.setOwner(1L);
        topic.setCreatedAt(new Date());
        topic.setUpdatedAt(new Date());

        TopicDto topicDto = new TopicDto();
        topicDto.setTitle("Unit test");
        topicDto.setOwner_id(1L);
        topicDto.setCreatedAt(new Date());
        topicDto.setUpdatedAt(new Date());

        when(topicRepository.findById(1L)).thenReturn(Optional.ofNullable(topic));
        when(topicRepository.save(Mockito.any(Topic.class))).thenReturn(topic);

        TopicDto savedTopic = topicService.updateTopic(topicDto, 1L);

        Assertions.assertThat(savedTopic).isNotNull();
    }

    @Test
    public void TopicService_DeleteTopicById_ReturnsTopicDto() {
        Topic topic = new Topic();
        topic.setTitle("Unit test");
        topic.setOwner(1L);
        topic.setCreatedAt(new Date());
        topic.setUpdatedAt(new Date());

        when(topicRepository.findById(1L)).thenReturn(Optional.ofNullable(topic));

        assertAll(() -> topicService.deleteTopic(1L));
    }

}
