package com.mapservice.repository;

import com.mapservice.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {
    @Query("select t from Tag t  where t.name IN :tags")
    List<Tag> findTagsByNamesIn(List<String> tags);

    Optional<Tag> findTagByName(String name);
}
