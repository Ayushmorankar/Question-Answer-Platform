package com.example.ayush.questionanswerplatform.controller;

import com.example.ayush.questionanswerplatform.dto.tag.TagRequest;
import com.example.ayush.questionanswerplatform.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/tags")
@AllArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<Void> createTag(@Valid @RequestBody TagRequest tagRequest){
        tagService.createTag(tagRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/{updatedName}")
    public ResponseEntity<Void> updateTag(@PathVariable Long id, @PathVariable String updatedName){
        tagService.updateTag(id, updatedName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id){
        tagService.deleteTag(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
