package com.example.ayush.questionanswerplatform.controller;

import com.example.ayush.questionanswerplatform.dto.topic.SubTopicRequest;
import com.example.ayush.questionanswerplatform.dto.topic.TopicRequest;
import com.example.ayush.questionanswerplatform.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/topics")
@AllArgsConstructor
public class TopicController {

    private final TopicService topicService;


    @PostMapping
    public ResponseEntity<Void> createTopic(@Valid @RequestBody TopicRequest topicRequest){
        topicService.createTopic(topicRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id){
        topicService.deleteTopic(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/{updatedName}")
    public ResponseEntity<Void> updateTopic(@PathVariable Long id, @PathVariable String updatedName){
        topicService.updateTopic(id, updatedName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/subtopics")
    public ResponseEntity<Void> createSubTopic(@Valid @RequestBody SubTopicRequest subTopicRequest){
        topicService.createSubTopic(subTopicRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/subtopics/{subtopicId}")
    public ResponseEntity<Void> deleteSubTopic(@PathVariable Long subtopicId){
        topicService.deleteSubtopic(subtopicId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
