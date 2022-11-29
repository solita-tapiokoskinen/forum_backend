package com.example.forum_backend.Comment;

import com.example.forum_backend.Exceptions.CommentNotFoundException;
import com.example.forum_backend.Exceptions.TopicNotFoundException;
import com.example.forum_backend.Topic.Topic;
import com.example.forum_backend.Topic.TopicDto;
import com.example.forum_backend.Topic.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;
    private TopicRepository topicRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, TopicRepository topicRepository) {
        this.commentRepository = commentRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public CommentDto addComment(Long topicId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        Topic topic = topicRepository.findById(topicId).orElseThrow(()->new TopicNotFoundException(("Topic with associated comment not found")));
        comment.setTopic(topic);
        comment.setCreatedAt(new Date());
        comment.setUpdatedAt(new Date());

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

        comment.setComment(commentDto.getComment());
        comment.setUpdatedAt(new Date());

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
        commentDto.setOwner(comment.getOwner());
        commentDto.setComment(comment.getComment());
        commentDto.setCreatedAt(comment.getCreatedAt());
        commentDto.setUpdatedAt(comment.getUpdatedAt());

        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setComment(commentDto.getComment());
        comment.setOwner(commentDto.getOwner());
        comment.setCreatedAt(commentDto.getCreatedAt());
        comment.setUpdatedAt(commentDto.getUpdatedAt());

        return comment;
    }

}
