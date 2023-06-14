package com.mapservice.service;

import com.mapservice.dto.CreateTagRequest;
import com.mapservice.dto.TagDto;
import com.mapservice.entity.Tag;
import com.mapservice.exception.MapServiceException;
import com.mapservice.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public UUID createTag(CreateTagRequest createTagRequest){
        Tag tag = new Tag();
        tag.setId(UUID.randomUUID());
        tag.setName(createTagRequest.getTagName());
       return tagRepository.save(tag).getId();
    }

    public TagDto getTagByName(String tagName){
        Optional<Tag> tagByName = tagRepository.findTagByName(tagName);
        Tag tag = tagByName.orElseThrow(() -> new MapServiceException("Tag with name does not exist", HttpStatus.NOT_FOUND));
        TagDto tagDto=new TagDto();
        tagDto.setId(tag.getId());
        tagDto.setName(tag.getName());
        return tagDto;
    }
}
