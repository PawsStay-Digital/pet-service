package com.pawsstay.petservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String breed;
    private Integer age;
    private String description;
    private String ownerEmail;
}
