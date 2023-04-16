package com.mapservice.controller;

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

import java.util.ArrayList;
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
        List<Restaurant> allRestaurants1 = restaurantService.getAllRestaurants();
        List<RestaurantDto> allRestaurants = new ArrayList<>();
        for (Restaurant restaurant: allRestaurants1
             ) { RestaurantDto restaurantDto = new RestaurantDto();
            restaurantDto.setId(restaurant.getId());
            restaurantDto.setName(restaurant.getName());
            restaurantDto.setCategory(restaurant.getCategory());
            restaurantDto.setAddress(restaurant.getAddress());
            restaurantDto.setLatitude(restaurant.getLatitude());
            restaurantDto.setLongitude(restaurant.getLongitude());
            allRestaurants.add(restaurantDto);
        }
        return allRestaurants;
    }

    @PreAuthorize("hasRole('ADMIN')")
    
    @PostMapping
    public UUID saveRestaurant(@RequestBody Restaurant restaurant) {
        UUID restaurantID = restaurantService.saveRestaurant(restaurant);
        return restaurantID;
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
        Restaurant restaurantById = restaurantService.getRestaurantById(id);
        return restaurantById;
    }

}
