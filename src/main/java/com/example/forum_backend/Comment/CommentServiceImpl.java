package com.example.forum_backend.Comment;

import com.example.forum_backend.Exceptions.CommentNotFoundException;
import com.example.forum_backend.Exceptions.TopicNotFoundException;
import com.example.forum_backend.Topic.Topic;
import com.example.forum_backend.Topic.TopicDto;
import com.example.forum_backend.Topic.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);

    }

    @Override
    public List<CommentDto> getCommentsByTopic(long topicId) {
        List<Comment> topicComments = commentRepository.findCommentByTopicId(topicId);

        return topicComments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(("Comment not found")));

        comment.setComment(commentDto.getComment());

        Comment updatedComment = commentRepository.save(comment);
        return mapToDto((updatedComment));
    }

    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setOwner(comment.getOwner());
        commentDto.setComment(comment.getComment());

        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setComment(commentDto.getComment());
        comment.setOwner(commentDto.getOwner());

        return comment;
    }

}
