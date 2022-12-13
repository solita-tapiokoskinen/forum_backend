package com.example.forum_backend.Service;

import com.example.forum_backend.Comment.*;
import com.example.forum_backend.Topic.Topic;
import com.example.forum_backend.Topic.TopicDto;
import com.example.forum_backend.Topic.TopicRepository;

import com.example.forum_backend.UserEntity.UserEntity;
import com.example.forum_backend.UserEntity.UserRepository;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Arrays;
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
    @Mock
    private UserRepository userRepository;
    @Mock
    Authentication authentication = Mockito.mock(Authentication.class);
    @Mock
    SecurityContext securityContext = Mockito.mock(SecurityContext.class);
    @InjectMocks
    private CommentServiceImpl commentService;

    private Topic topic;
    private TopicDto topicDto;
    private Comment comment;
    private CommentDto commentDto;
    private UserEntity user;

    @BeforeEach
    public void init() {
        user = new UserEntity();
        user.setId(1L);

        topic = new Topic();
        topic.setId(1L);
        topic.setTitle("Unit test");
        topic.setOwner(user);
        topic.setCreatedAt(LocalDateTime.now());
        topic.setUpdatedAt(LocalDateTime.now());

        topicDto = new TopicDto();
        topic.setId(1L);
        topicDto.setTitle("Unit test");
        topicDto.setOwner_id(user.getId());
        topicDto.setCreatedAt(LocalDateTime.now());
        topicDto.setUpdatedAt(LocalDateTime.now());

        comment = new Comment();
        comment.setComment("Unit test");
        comment.setOwner(user);
        comment.setTopic(topic);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());

        commentDto = new CommentDto();
        commentDto.setOwner(user.getId());
        commentDto.setComment("Unit test");
        commentDto.setTopicId(topic.getId());
        commentDto.setCreatedAt(LocalDateTime.now());
        commentDto.setUpdatedAt(LocalDateTime.now());

    }

    @Test
    public void CommentService_CreateComment_ReturnsCommentDto() {

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn(user.getUsername());
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        when(topicRepository.findById(topic.getId())).thenReturn(Optional.of(topic));
        when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(comment);
        when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));

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

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn(user.getUsername());
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

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
