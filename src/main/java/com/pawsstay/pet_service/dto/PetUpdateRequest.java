package com.pawsstay.pet_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetUpdateRequest {
    @NotBlank
    private String name;
    private boolean isVaccinated;
    private boolean isNeutered;
    private boolean isTrained;
    private String notes;
}
