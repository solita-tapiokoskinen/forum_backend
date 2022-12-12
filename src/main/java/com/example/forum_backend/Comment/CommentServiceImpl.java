package com.example.forum_backend.Comment;

import com.example.forum_backend.Exceptions.CommentNotFoundException;
import com.example.forum_backend.Exceptions.TopicNotFoundException;
import com.example.forum_backend.Exceptions.UnathorizedException;
import com.example.forum_backend.Exceptions.UserNotFoundException;
import com.example.forum_backend.Topic.Topic;
import com.example.forum_backend.Topic.TopicRepository;
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
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;
    private TopicRepository topicRepository;
    private UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,
                              TopicRepository topicRepository,
                              UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommentDto addComment(long topicId, CommentDto commentDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserEntity user = userRepository.findByUsername(currentPrincipalName).orElseThrow(() -> new UserNotFoundException(("User not found")));
        commentDto.setOwner(user.getId());
        commentDto.setOwnerName(currentPrincipalName);
        Comment comment = mapToEntity(commentDto);

        Topic topic = topicRepository.findById(topicId).orElseThrow(()->new TopicNotFoundException(("Topic with associated comment not found")));
        comment.setTopic(topic);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);

    }

    @Override
    public CommentResponse getCommentsByTopic(long topicId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Comment> comments = commentRepository.findCommentByTopicId(topicId, pageable);
        List<Comment> listOfComments = comments.getContent();
        List<CommentDto> content = listOfComments.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setContent(content);
        commentResponse.setPageNo(comments.getNumber());
        commentResponse.setPageSize(comments.getSize());
        commentResponse.setTotalElements(comments.getTotalElements());
        commentResponse.setTotalPages(comments.getTotalPages());
        commentResponse.setLast(comments.isLast());

        return commentResponse;
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, long topicId, long commentId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException("Topic with associated comment not found"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment with associate topic not found"));

        if(comment.getTopic().getId() != topic.getId()) {
            throw new CommentNotFoundException("This comment does not belong to a topic");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserEntity user = userRepository.findByUsername(currentPrincipalName).orElseThrow(() -> new UserNotFoundException(("User not found")));

        if (comment.getOwner().getId() != user.getId()) {
            throw new UnathorizedException("You do not own this comment");
        }

        comment.setComment(commentDto.getComment());
        comment.setUpdatedAt(LocalDateTime.now());

        Comment updatedComment = commentRepository.save(comment);
        return mapToDto((updatedComment));
    }

    @Override
    public void deleteComment(long topicId, long commentId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException("Topic with associated comment not found"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment with associate topic not found"));

        if(comment.getTopic().getId() != topic.getId()) {
            throw new CommentNotFoundException("This comment does not belong to a topic");
        }

        commentRepository.delete(comment);
    }

    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setOwner(comment.getOwner().getId());
        commentDto.setOwnerName(comment.getOwner().getUsername());
        commentDto.setComment(comment.getComment());
        commentDto.setTopicId(comment.getTopic().getId());
        commentDto.setCreatedAt(comment.getCreatedAt());
        commentDto.setUpdatedAt(comment.getUpdatedAt());

        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setComment(commentDto.getComment());
        UserEntity user = userRepository.findById(commentDto.getOwner()).orElseThrow(() -> new UserNotFoundException(("User not found")));
        comment.setOwner(user);
        comment.setCreatedAt(commentDto.getCreatedAt());
        comment.setUpdatedAt(commentDto.getUpdatedAt());

        return comment;
    }

}
