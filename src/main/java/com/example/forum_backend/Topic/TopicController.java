package com.example.forum_backend.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<Topic> getTopic() {
        return topicService.getTopic();
    }

    @GetMapping("/topics/{id}")
    public Optional<Topic> topicDetail(@PathVariable Long id) {
        return topicService.findById(id);
    }

    @PostMapping("/addTopic")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTopic(@RequestBody Topic topic) { topicService.addNewTopic(topic);}

}
