package com.pawsstay.pet_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long ownerId;
    @Column(nullable = false)
    private String name;
    private String breed;
    private LocalDate birthday;
    private String gender;
    private boolean isNeutered;
    private boolean isVaccinated;
    private boolean isTrained;
    private String chipNumber;
    @Column(length = 1000)
    private String photoUrl;
    @Column(columnDefinition = "TEXT")
    private String notes;

}
