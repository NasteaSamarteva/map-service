package com.mapservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class AddCommentRequest {
    private UUID restaurantId;
    private String comment;
}
