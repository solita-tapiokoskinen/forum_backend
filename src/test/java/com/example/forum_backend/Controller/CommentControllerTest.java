package com.example.forum_backend.Controller;

import com.example.forum_backend.Comment.*;
import com.example.forum_backend.Topic.Topic;
import com.example.forum_backend.Topic.TopicDto;
import com.example.forum_backend.Topic.TopicResponse;
import com.example.forum_backend.UserEntity.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = CommentController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CommentService commentService;
    @Autowired
    private ObjectMapper objectMapper;

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
        topic.setCreatedAt(new Date());
        topic.setUpdatedAt(new Date());

        topicDto = new TopicDto();
        topicDto.setId(1L);
        topicDto.setTitle("Unit test");
        topicDto.setOwner_id(user.getId());
        topicDto.setCreatedAt(new Date());
        topicDto.setUpdatedAt(new Date());

        comment = new Comment();
        comment.setComment("Unit test");
        comment.setOwner(user);
        comment.setCreatedAt(new Date());
        comment.setUpdatedAt(new Date());

        commentDto = new CommentDto();
        commentDto.setOwner(user.getId());
        commentDto.setComment("Unit test");
        commentDto.setCreatedAt(new Date());
        commentDto.setUpdatedAt(new Date());

    }

    @Test
    public void CommentController_GetAllCommentsByTopic_ReturnCommentDto() throws Exception {

        CommentResponse responseDto = new CommentResponse();
        responseDto.setPageNo(1);
        responseDto.setPageSize(10);
        responseDto.setLast(true);
        responseDto.setContent(Arrays.asList(commentDto));

        long topicId = 1L;
        when(commentService.getCommentsByTopic(topicId, 1, 10)).thenReturn(responseDto);

        ResultActions response = mockMvc.perform(get("/api/topics/1/comments")
                .param("pageNo", "1")
                .param("pageSize", "10"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()", CoreMatchers.is(responseDto.getContent().size())))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void CommentController_CreateComment_ReturnCommentDto() throws Exception {

        long topicId = 1L;
        when(commentService.addComment(topicId,commentDto)).thenReturn(commentDto);


        ResultActions response = mockMvc.perform(post("/api/topics/1/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                //.andExpect(MockMvcResultMatchers.jsonPath("$.comment", CoreMatchers.is(commentDto.getComment())))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void CommentController_DeleteComment_ReturnString() throws Exception {
        Long topicId = 1L;
        Long commentId = 1L;
        doNothing().when(commentService).deleteComment(topicId, commentId);

        ResultActions response = mockMvc.perform(delete("/api/topics/1/comments/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

}
