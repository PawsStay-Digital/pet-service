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
public class PetUpdateRequest {
    @NotNull
    private Long ownerId;
    @NotBlank
    private String name;
    private String breed;
    @NotNull
    private LocalDate birthday;
    private boolean isVaccinated;
    private String chipNumber;
    private boolean isNeutered;
    private boolean isTrained;
    private String photoUrl;
    private String notes;
}
