package com.example.forum_backend.Comment;

import com.example.forum_backend.Topic.TopicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto addComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setOwner(commentDto.getOwner());
        comment.setTopicId(commentDto.getTopicId());
        comment.setComment(commentDto.getComment());

        Comment newComment = commentRepository.save(comment);

        CommentDto commentResponse = new CommentDto();
        commentResponse.setId(newComment.getId());
        commentResponse.setOwner(newComment.getOwner());
        commentResponse.setTopicId(commentDto.getTopicId());
        commentResponse.setComment(newComment.getComment());

        return commentResponse;

    }

    @Override
    public List<CommentDto> getCommentsByTopic(Long topicId) {
        List<Comment> topicComments = commentRepository.findByTopicId(topicId);

        return topicComments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
    }

    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setTopicId(comment.getTopicId());
        commentDto.setOwner(comment.getOwner());
        commentDto.setComment(comment.getComment());

        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setComment(commentDto.getComment());
        comment.setOwner(comment.getOwner());
        comment.setTopicId(commentDto.getTopicId());

        return comment;
    }

}
