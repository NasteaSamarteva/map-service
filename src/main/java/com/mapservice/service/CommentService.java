package com.mapservice.service;

import com.mapservice.dto.AddCommentRequest;
import com.mapservice.dto.CommentDto;
import com.mapservice.entity.Comment;
import com.mapservice.entity.Restaurant;
import com.mapservice.exception.MapServiceException;
import com.mapservice.repository.CommentRepository;
import com.mapservice.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public void addCommentToRestaurant(AddCommentRequest addCommentRequest, String username) {
        Optional<Restaurant> restaurantById = restaurantRepository.findById(addCommentRequest.getRestaurantId());
        Restaurant restaurant = restaurantById.orElseThrow(() -> new MapServiceException("Restaurant with id %s does not exist".formatted(addCommentRequest.getRestaurantId()), HttpStatus.NOT_FOUND));
        Comment comment = new Comment();
        String message = addCommentRequest.getComment();
        comment.setMessage(message);
        comment.setId(UUID.randomUUID());
        comment.setRestaurant(restaurant);
        comment.setUsername(username);
        commentRepository.save(comment);

    }

    public void deleteComment(Authentication authentication, UUID commentId) {
        Optional<Comment> commentById = commentRepository.findById(commentId);
        Comment comment = commentById.orElseThrow(() -> new MapServiceException("Comment with id %s does not exist".formatted(commentId), HttpStatus.NOT_FOUND));
        checkPermissionDeleteComment(authentication, comment);
        commentRepository.delete(comment);
    }

    private void checkPermissionDeleteComment(Authentication authentication, Comment comment) {
        SimpleGrantedAuthority authority = (SimpleGrantedAuthority) authentication.getAuthorities().toArray()[0];
        //проверка на роль пользователя, если админ, то может удалять все коменты,
        if (authority.getAuthority().equals("ROLE_ADMIN")) {
            return;
        }
        //если комент принадлежит пользователю, а не админу, то удаляет только свой комент
        if (!comment.getUsername().equals(authentication.getName())) {
            throw new MapServiceException("This comment doesn't belong to user %s".formatted(authentication.getName()), HttpStatus.FORBIDDEN);
        }
    }

    public List<CommentDto> getRestaurantComments(UUID idRestaurant) {
        if (!restaurantRepository.existsById(idRestaurant)) {
            throw new MapServiceException("Restaurant does not exist", HttpStatus.NOT_FOUND);
        }

        return commentRepository.findByRestaurantId(idRestaurant).stream().map(this::mapCommentToDto).toList();
    }

    private CommentDto mapCommentToDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setComment(comment.getMessage());
        dto.setId(comment.getId());
        return dto;

    }
}
