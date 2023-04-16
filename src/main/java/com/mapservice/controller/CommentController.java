package com.mapservice.controller;

import com.mapservice.dto.AddCommentRequest;
import com.mapservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping
    @RequestMapping("/add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addCommentToRestaurant(Authentication authentication, @RequestBody AddCommentRequest addCommentRequest){
        commentService.addCommentToRestaurant(addCommentRequest, authentication.getName());
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping
    @RequestMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(Authentication authentication, @PathVariable UUID id){
     commentService.deleteComment(authentication,id);
    }
}
