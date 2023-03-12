package com.mapservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Role {
    @Id
    private UUID id;
    private String name;
    @OneToMany(mappedBy = "role")
     private List<Customer> customer;
}
