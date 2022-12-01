package com.example.forum_backend.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) { this.commentService = commentService;}

    @GetMapping("/topics/{topicId}/comments")
    public ResponseEntity<CommentResponse> getAllCommentsByTopic(@PathVariable(value = "topicId") Long topicId,
                                              @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                              @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){

        return new ResponseEntity<>(commentService.getCommentsByTopic(topicId, pageNo, pageSize), HttpStatus.OK);
    }

    @PostMapping("/topics/{topicId}/comments")
    public ResponseEntity<CommentDto> addComment(
            @PathVariable(value = "topicId") long topicId,
            @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.addComment(topicId, commentDto), HttpStatus.CREATED);
    }

    @PutMapping("/topics/{topicId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto,
                                                    @PathVariable(value="topicId") long topicId,
                                                    @PathVariable(value="commentId") long commentId
                                                    ){
        CommentDto response = commentService.updateComment(commentDto, topicId, commentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/topics/{topicId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "topicId") long topicId, @PathVariable(value="commentId") long commentId) {
        commentService.deleteComment(topicId, commentId);
        return new ResponseEntity<>("Review deleted succesfully", HttpStatus.OK);
    }

}
