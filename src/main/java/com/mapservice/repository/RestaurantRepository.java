package com.mapservice.repository;

import com.mapservice.entity.Restaurant;
import com.mapservice.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
    void deleteById(UUID id);

    @Query("SELECT r FROM Restaurant r JOIN r.tags t WHERE t IN :tags GROUP BY r HAVING COUNT(DISTINCT t) = :count")
    List<Restaurant> findRestaurantByTags(List<Tag> tags, int count);
}
