package com.mapservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="Customers")
public class Customer {
    @Id // аннотация для primary key
    private UUID id;
    @Column(unique = true) // аннотация для уникакольной колонки в нашем случае для колонки username
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
