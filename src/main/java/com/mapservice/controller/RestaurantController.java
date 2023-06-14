package com.mapservice.controller;

import com.mapservice.dto.AssignTagToRestaurantRequest;
import com.mapservice.dto.GetRestaurantByTagRequest;
import com.mapservice.dto.RestaurantDto;
import com.mapservice.dto.RestaurantUpdateRequest;
import com.mapservice.entity.Restaurant;
import com.mapservice.entity.Tag;
import com.mapservice.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public List<RestaurantDto> getAllRestaurants() {
        return restaurantService.getAllRestaurants().stream()
                .map(this::mapRestaurantToDto)
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public UUID saveRestaurant(@RequestBody RestaurantDto restaurantDto) {
        return restaurantService.saveRestaurant(restaurantDto);
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
    public RestaurantDto getRestaurantById(@PathVariable UUID id) {
        return mapRestaurantToDto(restaurantService.getRestaurantById(id));
    }

    @GetMapping("/byTag")
    public List<RestaurantDto> getRestaurantsByTags(@RequestBody GetRestaurantByTagRequest getRestaurantByTagRequest) {
        return restaurantService.getRestaurantsByTags(getRestaurantByTagRequest).stream()
                .map(this::mapRestaurantToDto)
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/assignTagToRestaurant")
    public void assignTagToRestaurant(@RequestBody AssignTagToRestaurantRequest assignTagToRestaurantRequest) {
        restaurantService.assignTagToRestaurant(assignTagToRestaurantRequest);
    }


    private RestaurantDto mapRestaurantToDto(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setName(restaurant.getName());
        restaurantDto.setCategory(restaurant.getCategory());
        restaurantDto.setAddress(restaurant.getAddress());
        restaurantDto.setLatitude(restaurant.getLatitude());
        restaurantDto.setLongitude(restaurant.getLongitude());
        List<String> tags = restaurant.getTags().stream().map(Tag::getName).toList();
        restaurantDto.setTags(tags);

        return restaurantDto;
    }

}
