package com.example.forum_backend.Comment;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) { this.commentRepository = commentRepository;}

    public Optional<Comment> getCommentsByTopic(Long topicId) {
        return commentRepository.findByTopicId(topicId);
    }

    public void postComment(Comment comment) {

        commentRepository.save(comment);

    }
}
