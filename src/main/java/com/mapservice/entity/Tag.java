package com.mapservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Entity
@Getter
@Setter
public class Tag {
    @Id
    private UUID id;
    private String name;
    @ManyToMany(mappedBy = "tags")
    private List<Restaurant> restaurants;
}
