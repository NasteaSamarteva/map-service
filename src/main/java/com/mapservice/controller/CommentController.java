package com.mapservice.controller;

import com.mapservice.dto.AddCommentRequest;
import com.mapservice.dto.CommentDto;
import com.mapservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public void addCommentToRestaurant(Authentication authentication, @RequestBody AddCommentRequest addCommentRequest) {
        commentService.addCommentToRestaurant(addCommentRequest, authentication.getName());
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(Authentication authentication, @PathVariable UUID id) {
        commentService.deleteComment(authentication, id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{idRestaurant}")
    public List<CommentDto> getRestaurantComments(@PathVariable UUID idRestaurant) {
        return commentService.getRestaurantComments(idRestaurant);
    }
}