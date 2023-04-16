package com.mapservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class GetRestaurantByTagRequest {
    private List<String> tags;
}
