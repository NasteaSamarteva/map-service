package com.mapservice.controller;

import com.mapservice.dto.GetRestaurantByTagRequest;
import com.mapservice.dto.RestaurantDto;
import com.mapservice.dto.RestaurantUpdateRequest;
import com.mapservice.entity.Restaurant;
import com.mapservice.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    @RequestMapping("/all")
    public List<RestaurantDto> getAllRestaurants() {
        return restaurantService.getAllRestaurants().stream()
                .map(this::mapRestaurantToDto)
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public UUID saveRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.saveRestaurant(restaurant);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    @RequestMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRestaurant(@PathVariable UUID id) {
        restaurantService.delete(id);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRestaurantInfo(@PathVariable UUID id, @RequestBody RestaurantUpdateRequest restaurantUpdateRequest) {
        restaurantService.updateRestaurantInfo(id, restaurantUpdateRequest);
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable UUID id) {
        return restaurantService.getRestaurantById(id);
    }

    @GetMapping("/byTag")
    public List<RestaurantDto> getRestaurantsByTags(@RequestBody GetRestaurantByTagRequest getRestaurantByTagRequest) {
        return restaurantService.getRestaurantsByTags(getRestaurantByTagRequest).stream()
                .map(this::mapRestaurantToDto)
                .toList();
    }

    private RestaurantDto mapRestaurantToDto(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setName(restaurant.getName());
        restaurantDto.setCategory(restaurant.getCategory());
        restaurantDto.setAddress(restaurant.getAddress());
        restaurantDto.setLatitude(restaurant.getLatitude());
        restaurantDto.setLongitude(restaurant.getLongitude());

        return restaurantDto;
    }

}
