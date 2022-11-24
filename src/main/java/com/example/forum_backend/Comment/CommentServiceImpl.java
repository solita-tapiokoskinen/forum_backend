package com.example.forum_backend.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
