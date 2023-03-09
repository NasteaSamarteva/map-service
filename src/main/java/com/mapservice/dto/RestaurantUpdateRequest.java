package com.mapservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantUpdateRequest {
    private String name;
    private String address;
    private Float latitude;
    private Float longitude;
}
