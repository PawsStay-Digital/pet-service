package com.pawsstay.petservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String breed;
    private Integer age;
    @Column(length = 1000)
    private String description;
    @Column(name = "owner_id", nullable = false)
    private String userId;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
