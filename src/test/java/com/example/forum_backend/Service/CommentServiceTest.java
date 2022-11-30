package com.example.forum_backend.Service;

import com.example.forum_backend.Comment.*;
import com.example.forum_backend.Topic.Topic;
import com.example.forum_backend.Topic.TopicDto;
import com.example.forum_backend.Topic.TopicRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;
    @Mock
    private TopicRepository topicRepository;
    @InjectMocks
    private CommentServiceImpl commentService;

    private Topic topic;
    private TopicDto topicDto;
    private Comment comment;
    private CommentDto commentDto;

    @BeforeEach
    public void init() {
        topic = new Topic();
        topic.setTitle("Unit test");
        topic.setOwner(1L);
        topic.setCreatedAt(new Date());
        topic.setUpdatedAt(new Date());

        topicDto = new TopicDto();
        topicDto.setTitle("Unit test");
        topicDto.setOwner_id(1L);
        topicDto.setCreatedAt(new Date());
        topicDto.setUpdatedAt(new Date());

        comment = new Comment();
        comment.setComment("Unit test");
        comment.setOwner(1L);
        comment.setCreatedAt(new Date());
        comment.setUpdatedAt(new Date());

        commentDto = new CommentDto();
        commentDto.setOwner(1L);
        commentDto.setComment("Unit test");
        commentDto.setCreatedAt(new Date());
        commentDto.setUpdatedAt(new Date());

    }

    @Test
    public void CommentService_CreateComment_ReturnsCommentDto() {

        when(topicRepository.findById(topic.getId())).thenReturn(Optional.of(topic));
        when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(comment);

        CommentDto savedComment = commentService.addComment(topic.getId(), commentDto);

        Assertions.assertThat(savedComment).isNotNull();

    }

    @Test
    public void CommentService_FindCommentsByTopicId_ReturnsCommentDto() {
        long topicId = 1L;
        Page<Comment> comments = Mockito.mock(Page.class);

        when(commentRepository.findCommentByTopicId(eq(topicId), Mockito.any(Pageable.class))).thenReturn(comments);

        CommentResponse savedComment = commentService.getCommentsByTopic(topicId, 1, 10);

        Assertions.assertThat(savedComment).isNotNull();

    }

    @Test
    public void CommentService_UpdatedComment_ReturnCommentDto() {
        long topicId = 1L;
        long commentId = 1L;
        topic.setComments(Arrays.asList(comment));
        comment.setTopic(topic);

        when(topicRepository.findById(topicId)).thenReturn(Optional.of(topic));
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
        when(commentRepository.save(comment)).thenReturn(comment);

        CommentDto updateReturn = commentService.updateComment(commentDto, topicId, commentId);

        Assertions.assertThat(updateReturn).isNotNull();

    }

    @Test
    public void CommentService_DeteleCommentByTopicIdAndCommentId_ReturnVoid() {
        long topicId = 1L;
        long commentId = 1L;

        topic.setComments(Arrays.asList(comment));
        comment.setTopic(topic);

        when(topicRepository.findById(topicId)).thenReturn(Optional.of(topic));
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));

        assertAll(()-> commentService.deleteComment(topicId, commentId));

    }

}
