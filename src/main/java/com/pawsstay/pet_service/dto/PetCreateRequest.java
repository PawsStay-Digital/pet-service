package com.pawsstay.pet_service.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetCreateRequest {
    @NotNull(message = "Owner ID is required")
    private Long ownerId;
    @NotBlank(message = "Pet name is required")
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
