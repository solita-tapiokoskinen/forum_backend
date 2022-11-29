package com.example.forum_backend.repository;

import com.example.forum_backend.Topic.Topic;
import com.example.forum_backend.Topic.TopicRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TopicRepositoryTest {

    @Autowired
    private TopicRepository topicRepository;

    @Test
    public void TopicRepository_SaveAll_ReturnSavedTopic() {

        //Arrange
        Topic topic = new Topic();
        topic.setTitle("Unit test");
        topic.setOwner(1L);
        topic.setCreatedAt(new Date());
        topic.setUpdatedAt(new Date());

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
        topic.setOwner(1L);
        topic.setCreatedAt(new Date());
        topic.setUpdatedAt(new Date());
        System.out.println(topic);

        Topic topic2 = new Topic();
        topic2.setTitle("Unit test 2");
        topic2.setOwner(1L);
        topic2.setCreatedAt(new Date());
        topic2.setUpdatedAt(new Date());
        System.out.println(topic2);

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
        topic.setOwner(1L);
        topic.setCreatedAt(new Date());
        topic.setUpdatedAt(new Date());
        System.out.println(topic);

        Topic testTopic = topicRepository.save(topic);

        Topic topicList = topicRepository.findById(testTopic.getId()).get();

        Assertions.assertThat(topicList).isNotNull();
    }

    @Test
    public void TopicRepository_UpdateTopic_ReturnTopicNotNull() {

        Topic topic = new Topic();
        topic.setTitle("Unit test");
        topic.setOwner(1L);
        topic.setCreatedAt(new Date());
        topic.setUpdatedAt(new Date());
        System.out.println(topic);

        topicRepository.save(topic);

        Topic topicSave = topicRepository.findById(topic.getId()).get();
        topicSave.setTitle("Updated title");
        topicSave.setUpdatedAt(new Date());

        Topic updatedTopic = topicRepository.save(topicSave);

        Assertions.assertThat(updatedTopic.getTitle()).isNotNull();
        Assertions.assertThat(updatedTopic.getUpdatedAt()).isNotNull();
    }

    @Test
    public void TopicRepository_DeleteTopic_ReturnTopicIsEmpty() {
        Topic topic = new Topic();
        topic.setTitle("Unit test");
        topic.setOwner(1L);
        topic.setCreatedAt(new Date());
        topic.setUpdatedAt(new Date());

        topicRepository.save(topic);

        topicRepository.deleteById(topic.getId());
        Optional<Topic> topicReturn = topicRepository.findById(topic.getId());

        Assertions.assertThat(topicReturn).isEmpty();
    }

}
