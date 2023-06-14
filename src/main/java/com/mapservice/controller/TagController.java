package com.mapservice.controller;

import com.mapservice.dto.CreateTagRequest;
import com.mapservice.dto.TagDto;
import com.mapservice.entity.Tag;
import com.mapservice.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.imageio.plugins.tiff.TIFFTagSet;
import java.util.UUID;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @RequestMapping("/create")
    public UUID createTag(@RequestBody CreateTagRequest createTagRequest){
        return tagService.createTag(createTagRequest);
    }

    @GetMapping
    @RequestMapping("/name/{tagName}")
    public TagDto getTagByName(@PathVariable String tagName){
        return tagService.getTagByName(tagName);
    }


}
