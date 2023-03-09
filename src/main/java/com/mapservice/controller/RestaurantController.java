package com.mapservice.controller;

import com.mapservice.dto.RestaurantUpdateRequest;
import com.mapservice.entity.Restaurant;
import com.mapservice.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    @RequestMapping("/all")
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> allRestaurants = restaurantService.getAllRestaurants();
        return allRestaurants;
    }

    @PostMapping
    public UUID saveRestaurant(@RequestBody Restaurant restaurant) {
        UUID restaurantID = restaurantService.saveRestaurant(restaurant);
        return restaurantID;
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRestaurant(@PathVariable UUID id) {
        restaurantService.delete(id);
    }

    @PutMapping
    @RequestMapping("/{id}")
    public void updateRestaurantInfo(@PathVariable UUID id, @RequestBody RestaurantUpdateRequest restaurantUpdateRequest) {

    }
}
