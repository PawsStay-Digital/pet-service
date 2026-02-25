package com.pawsstay.petservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetRequest {
    @NotBlank(message = "pet name not be empty")
    @Size(max = 50)
    private String name;

    @NotBlank(message = "breed not be empty")
    private String breed;

    @Min(value = 0, message = "age must above 0")
    private Integer age;

    @Size(max = 500, message = "description max 500")
    private String description;
}
