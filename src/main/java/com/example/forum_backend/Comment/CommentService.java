package com.example.forum_backend.Comment;

import java.util.List;

public interface CommentService {

    CommentDto addComment(CommentDto commentDto);

    List<CommentDto> getCommentsByTopic(Long topicId);

    CommentDto updateComment(CommentDto commentDto, long id);
}
