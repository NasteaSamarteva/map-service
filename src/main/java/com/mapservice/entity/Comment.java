package com.mapservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    private UUID id;
    private String message;
    private String username;
    @ManyToOne
    private Restaurant restaurant;
}
