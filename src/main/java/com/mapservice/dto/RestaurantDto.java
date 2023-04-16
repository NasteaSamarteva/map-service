package com.mapservice.dto;

import com.mapservice.entity.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RestaurantDto {
    private UUID id;
    private String name;
    private String address;
    private Float latitude;
    private Float longitude;
    private Category category;
}
