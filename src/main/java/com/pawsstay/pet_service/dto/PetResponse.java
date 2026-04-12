package com.pawsstay.pet_service.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetResponse {
    private Long id;
    private Long ownerId;
    private String name;
    private String breed;
    private LocalDate birthday;
    private String gender;
    private boolean isVaccinated;
    private String chipNumber;
    private boolean isNeutered;
    private boolean isTrained;
    private String photoUrl;
    private String notes;
}
