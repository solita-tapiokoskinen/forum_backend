package com.example.forum_backend.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/topics")
    public ResponseEntity<TopicResponse> getAllTopics(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                      @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {

        return new ResponseEntity<>(topicService.getAllTopics(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/topics/{id}")
    public ResponseEntity<TopicDto> topicDetail(@PathVariable Long id) {

        return ResponseEntity.ok(topicService.getTopicById(id));
    }

    @PostMapping("/topics/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TopicDto> addTopic(@RequestBody TopicDto topicDto) {
        return new ResponseEntity<>(topicService.addTopic(topicDto), HttpStatus.CREATED);
    }

    @PutMapping("/topics/{id}/update")
    public ResponseEntity<TopicDto> updateTopic(@RequestBody TopicDto topicDto, @PathVariable("id") long id) {
        TopicDto response = topicService.updateTopic(topicDto, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/topics/{id}/delete")
    public ResponseEntity<String> deleteTopic(@PathVariable long id) {
        topicService.deleteTopic(id);
        return new ResponseEntity<>("Topic deleted", HttpStatus.OK);
    }

}
