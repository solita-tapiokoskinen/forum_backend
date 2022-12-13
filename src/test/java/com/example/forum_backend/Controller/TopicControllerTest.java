package com.example.forum_backend.Controller;

import com.example.forum_backend.Comment.Comment;
import com.example.forum_backend.Comment.CommentDto;
import com.example.forum_backend.Topic.*;
import com.example.forum_backend.UserEntity.UserEntity;
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

import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = TopicController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TopicControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CustomUserDetailsService customUserDetailsService;
    @MockBean
    JwtAuthEntryPoint jwtAuthEntryPoint;
    @MockBean
    JWTGenerator jwtGenerator;
    @MockBean
    private TopicService topicService;
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
        topic.setCreatedAt(ZonedDateTime.now());
        topic.setUpdatedAt(ZonedDateTime.now());

        topicDto = new TopicDto();
        topicDto.setId(1L);
        topicDto.setTitle("Unit test");
        topicDto.setOwner_id(user.getId());
        topicDto.setCreatedAt(ZonedDateTime.now());
        topicDto.setUpdatedAt(ZonedDateTime.now());

        comment = new Comment();
        comment.setComment("Unit test");
        comment.setOwner(user);
        comment.setTopic(topic);
        comment.setCreatedAt(ZonedDateTime.now());
        comment.setUpdatedAt(ZonedDateTime.now());

        commentDto = new CommentDto();
        commentDto.setOwner(user.getId());
        commentDto.setComment("Unit test");
        commentDto.setTopicId(topic.getId());
        commentDto.setCreatedAt(ZonedDateTime.now());
        commentDto.setUpdatedAt(ZonedDateTime.now());

    }

    @Test
    public void TopicController_CreateTopic_ReturnCreated() throws Exception {

        given(topicService.addTopic(any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/api/topics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(topicDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(topicDto.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.owner_id", CoreMatchers.is(topicDto.getOwner_id()), Long.class))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void TopicController_GetAllTopics_ReturnResponseDto() throws Exception {

        TopicResponse responseDto = new TopicResponse();
        responseDto.setPageNo(1);
        responseDto.setPageSize(10);
        responseDto.setLast(true);
        responseDto.setContent(Arrays.asList(topicDto));
        when(topicService.getAllTopics(1,10)).thenReturn(responseDto);

        ResultActions response = mockMvc.perform(get("/api/topics")
                .param("pageNo", "1")
                .param("pageSize", "10"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()", CoreMatchers.is(responseDto.getContent().size())));

    }

    @Test
    public void TopicController_TopicDetail_ReturnResponseDto() throws Exception {

        long topicId = 1L;
        when(topicService.getTopicById(topicId)).thenReturn(topicDto);

        ResultActions response = mockMvc.perform(get("/api/topics/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(topicDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(topicDto.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.owner_id", CoreMatchers.is(topicDto.getOwner_id()), Long.class))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void TopicController_UpdateTopic_ReturnTopicDto() throws Exception {
        when(topicService.updateTopic(any(),eq(1L))).thenReturn(topicDto);

        ResultActions response = mockMvc.perform(put("/api/topics/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(topicDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(topicDto.getTitle())))
                .andDo(MockMvcResultHandlers.print());;

    }

    @Test
    public void TopicController_DeleteTopic_ReturnString() throws Exception {
        Long id = 1L;
        doNothing().when(topicService).deleteTopic(id);

        ResultActions response = mockMvc.perform(delete("/api/topics/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

}
