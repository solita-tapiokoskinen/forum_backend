package com.example.forum_backend.Controller;

import com.example.forum_backend.Comment.*;
import com.example.forum_backend.Topic.Topic;
import com.example.forum_backend.Topic.TopicDto;
import com.example.forum_backend.security.CustomUserDetailsService;
import com.example.forum_backend.security.JWTGenerator;
import com.example.forum_backend.security.JwtAuthEntryPoint;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = CommentController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CustomUserDetailsService customUserDetailsService;
    @MockBean
    JwtAuthEntryPoint jwtAuthEntryPoint;
    @MockBean
    JWTGenerator jwtGenerator;
    @MockBean
    private CommentService commentService;
    @Autowired
    private ObjectMapper objectMapper;

    private Topic topic;
    private TopicDto topicDto;
    private Comment comment;
    private CommentDto commentDto;

    @BeforeEach
    public void init() {

        topic = new Topic();
        topic.setId(1L);
        topic.setTitle("Unit test");
        topic.setCreatedAt(LocalDateTime.now());
        topic.setUpdatedAt(LocalDateTime.now());

        topicDto = new TopicDto();
        topicDto.setId(1L);
        topicDto.setTitle("Unit test");
        topicDto.setCreatedAt(LocalDateTime.now());
        topicDto.setUpdatedAt(LocalDateTime.now());

        comment = new Comment();
        comment.setId(1L);
        comment.setComment("Unit test");
        comment.setTopic(topic);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());

        commentDto = new CommentDto();
        commentDto.setId(1L);
        commentDto.setComment("Unit test");
        commentDto.setTopicId(comment.getTopic().getId());
        commentDto.setCreatedAt(LocalDateTime.now());
        commentDto.setUpdatedAt(LocalDateTime.now());

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
    public void CommentController_UpdateComment_ReturnCommentDto() throws Exception {

        long topicId = 1L;
        when(commentService.updateComment(commentDto, commentDto.getTopicId(), comment.getId())).thenReturn(commentDto);

        ResultActions response = mockMvc.perform(put("/api/topics/1/comments/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
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
