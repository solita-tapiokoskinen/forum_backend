package com.example.forum_backend.Comment;

import java.util.List;

public interface CommentService {

    CommentDto addComment(Long topicId, CommentDto commentDto);

    List<CommentDto> getCommentsByTopic(long topicId);

    CommentDto updateComment(CommentDto commentDto, long topicId, long commentId);

    void deleteComment(long topicId, long commentId);
}
