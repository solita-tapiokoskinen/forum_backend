package com.example.forum_backend.Repository;

import com.example.forum_backend.Topic.Topic;
import com.example.forum_backend.Topic.TopicRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TopicRepositoryTest {

    @Autowired
    private TopicRepository topicRepository;

    @Test
    public void TopicRepository_SaveAll_ReturnSavedTopic() {

        //Arrange
        Topic topic = new Topic();
        topic.setTitle("Unit test");
        topic.setCreatedAt(LocalDateTime.now());
        topic.setUpdatedAt(LocalDateTime.now());

        //Act
        Topic testTopic = topicRepository.save(topic);

        //Assert
        Assertions.assertThat(testTopic).isNotNull();
        Assertions.assertThat(testTopic.getId()).isGreaterThan(0);
    }

    @Test
    public void TopicRepository_GetAll_ReturnMoreThanOneTopic() {

        Topic topic = new Topic();
        topic.setTitle("Unit test");
        topic.setCreatedAt(LocalDateTime.now());
        topic.setUpdatedAt(LocalDateTime.now());

        Topic topic2 = new Topic();
        topic2.setTitle("Unit test 2");
        topic2.setCreatedAt(LocalDateTime.now());
        topic2.setUpdatedAt(LocalDateTime.now());

        topicRepository.save(topic);
        topicRepository.save(topic2);

        List<Topic> topicList = topicRepository.findAll();

        Assertions.assertThat(topicList).isNotNull();
        Assertions.assertThat(topicList.size()).isEqualTo(2);

    }

    @Test
    public void TopicRepository_FindById_ReturnTopic() {

        Topic topic = new Topic();
        topic.setTitle("Unit test");
        topic.setCreatedAt(LocalDateTime.now());
        topic.setUpdatedAt(LocalDateTime.now());

        Topic testTopic = topicRepository.save(topic);

        Topic topicList = topicRepository.findById(testTopic.getId()).get();

        Assertions.assertThat(topicList).isNotNull();
    }

    @Test
    public void TopicRepository_UpdateTopic_ReturnTopicNotNull() {

        Topic topic = new Topic();
        topic.setTitle("Unit test");
        topic.setCreatedAt(LocalDateTime.now());
        topic.setUpdatedAt(LocalDateTime.now());

        topicRepository.save(topic);

        Topic topicSave = topicRepository.findById(topic.getId()).get();
        topicSave.setTitle("Updated title");
        topicSave.setUpdatedAt(LocalDateTime.now());

        Topic updatedTopic = topicRepository.save(topicSave);

        Assertions.assertThat(updatedTopic.getTitle()).isNotNull();
        Assertions.assertThat(updatedTopic.getUpdatedAt()).isNotNull();
    }

    @Test
    public void TopicRepository_DeleteTopic_ReturnTopicIsEmpty() {
        Topic topic = new Topic();
        topic.setTitle("Unit test");
        topic.setCreatedAt(LocalDateTime.now());
        topic.setUpdatedAt(LocalDateTime.now());

        topicRepository.save(topic);

        topicRepository.deleteById(topic.getId());
        Optional<Topic> topicReturn = topicRepository.findById(topic.getId());

        Assertions.assertThat(topicReturn).isEmpty();
    }

}
