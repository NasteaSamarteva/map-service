package com.mapservice.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "restaurant_tag",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;
}
