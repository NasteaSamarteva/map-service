package com.mapservice.service;

import com.mapservice.dto.AssignTagToRestaurantRequest;
import com.mapservice.dto.GetRestaurantByTagRequest;
import com.mapservice.dto.RestaurantDto;
import com.mapservice.dto.RestaurantUpdateRequest;
import com.mapservice.entity.Restaurant;
import com.mapservice.entity.Tag;
import com.mapservice.exception.MapServiceException;
import com.mapservice.repository.RestaurantRepository;
import com.mapservice.repository.TagRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private TagRepository tagRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public UUID saveRestaurant(RestaurantDto restaurantDto) {
        Restaurant saveRestaurant = new Restaurant();
        saveRestaurant.setName(restaurantDto.getName());
        saveRestaurant.setAddress(restaurantDto.getAddress());
        saveRestaurant.setCategory(restaurantDto.getCategory());
        saveRestaurant.setLatitude(restaurantDto.getLatitude());
        saveRestaurant.setLongitude(restaurantDto.getLongitude());
        saveRestaurant.setId(UUID.randomUUID());

        return restaurantRepository.save(saveRestaurant).getId();
    }

    public void delete(UUID id) {
        restaurantRepository.deleteById(id);
    }

    public void updateRestaurantInfo(UUID id, RestaurantUpdateRequest restaurantUpdateRequest) {
        Optional<Restaurant> restaurantToUpdate = restaurantRepository.findById(id);
        Restaurant restaurant = restaurantToUpdate.orElseThrow(() -> new MapServiceException("Restaurant with id %s does not exist".formatted(id), HttpStatus.NOT_FOUND));

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
                .orElseThrow(() -> new MapServiceException("Restaurant with id %s does not exist".formatted(id), HttpStatus.NOT_FOUND));

        return restaurant;
    }

    public List<Restaurant> getRestaurantsByTags(GetRestaurantByTagRequest getRestaurantByTagRequest) {
        List<Tag> tags = tagRepository.findTagsByNamesIn(getRestaurantByTagRequest.getTags());
        return restaurantRepository.findRestaurantByTags(tags, tags.size());
    }

    @Transactional
    public void assignTagToRestaurant(AssignTagToRestaurantRequest assignTagToRestaurantRequest){
        Optional<Restaurant> restaurantOptional= restaurantRepository.findById(assignTagToRestaurantRequest.getIdRestaurant());
        Optional<Tag> tagOptional= tagRepository.findById(assignTagToRestaurantRequest.getIdTag());
        Restaurant restaurant = restaurantOptional.orElseThrow(() -> new MapServiceException("Restaurant with such id does not exist", HttpStatus.NOT_FOUND));
        Tag tag = tagOptional.orElseThrow(() -> new MapServiceException("Tag with such id does not exist", HttpStatus.NOT_FOUND));
        restaurant.getTags().add(tag);
        tag.getRestaurants().add(restaurant);
    }
}
