package com.example.forum_backend.Topic;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api")
@CrossOrigin(origins = { "http://localhost:3000" })
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/topics")
    public ResponseEntity<List<TopicDto>> getAllTopics() {

        return new ResponseEntity<>(topicService.getAllTopics(), HttpStatus.OK);
    }

    @GetMapping("/topics/{id}")
    public ResponseEntity<TopicDto> topicDetail(@PathVariable Long id) {

        return ResponseEntity.ok(topicService.getTopicById(id));
    }

    @PostMapping("/topic/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TopicDto> addTopic(@RequestBody TopicDto topicDto) {
        return new ResponseEntity<>(topicService.addTopic(topicDto), HttpStatus.CREATED);
    }

}
