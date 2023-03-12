package com.mapservice.service;

import com.mapservice.dto.RestaurantUpdateRequest;
import com.mapservice.entity.Restaurant;
import com.mapservice.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public UUID saveRestaurant(Restaurant restaurant) {
        Restaurant saveRestaurant = restaurantRepository.save(restaurant);
        return saveRestaurant.getId();
    }

    public void delete(UUID id) {
        restaurantRepository.deleteById(id);
    }

    public void updateRestaurantInfo(UUID id, RestaurantUpdateRequest restaurantUpdateRequest) {
        Optional<Restaurant> restaurantToUpdate = restaurantRepository.findById(id);
        Restaurant restaurant = restaurantToUpdate.orElseThrow(() -> new NoSuchElementException("Restaurant with id %s does not exist".formatted(id)));

        if (restaurantUpdateRequest.getName() != null) {
            restaurant.setName(restaurantUpdateRequest.getName());
        }
        if (restaurantUpdateRequest.getAddress() != null) {
            restaurant.setAddress(restaurantUpdateRequest.getAddress());
        }
        if (restaurantUpdateRequest.getLatitude() != null) {
            restaurant.setLatitude(restaurantUpdateRequest.getLatitude());
        }
        if (restaurantUpdateRequest.getLongitude() != null) {
            restaurant.setLongitude(restaurantUpdateRequest.getLongitude());
        }
        restaurantRepository.save(restaurant);
    }


    public Restaurant getRestaurantById(UUID id) {
        Optional<Restaurant> byId = restaurantRepository.findById(id);
        Restaurant restaurant = byId
                .orElseThrow(() -> new NoSuchElementException("Restaurant with id %s does not exist".formatted(id)));

        return restaurant;


    }
}
