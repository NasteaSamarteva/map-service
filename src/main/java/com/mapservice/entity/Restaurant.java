package com.mapservice.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
//сущность, этот объект(то есть все поля) равны столбцам в базе данных.
// когда будет создоваться объект ресторан он будет брать все данные из столбцов базы данных
@Getter
@Setter
public class Restaurant {

    @Id
    private UUID id;
    private String name;
    private String address;
    private Float latitude;
    private Float longitude;
    private Category category;
    @OneToMany(mappedBy = "restaurant")
    private List<Comment> comments;
    @ManyToMany
    private List<Tag> tags;
}
