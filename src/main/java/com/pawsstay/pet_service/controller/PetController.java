package com.pawsstay.pet_service.controller;

import com.pawsstay.pet_service.dto.PetCreateRequest;
import com.pawsstay.pet_service.dto.PetResponse;
import com.pawsstay.pet_service.dto.PetUpdateRequest;
import com.pawsstay.pet_service.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;


@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
@Slf4j
public class PetController {
    private final PetService petService;
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Pet Service is running on Virtual Threads!");
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PetResponse> createPet(@RequestPart("metadata") @Valid PetCreateRequest request,
                                                 @RequestPart(value = "image", required = false) MultipartFile image){
        PetResponse pet= petService.createPet(request, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(pet);
    }
    @GetMapping("owner/{ownerId}")
    public ResponseEntity<Collection<PetResponse>> getPetsByOwner(@PathVariable Long ownerId){
        return ResponseEntity.ok(petService.findPetsByOwner(ownerId));
    }
    @GetMapping("{id}")
    public ResponseEntity<PetResponse> getPet(@PathVariable Long id){
        return ResponseEntity.ok(petService.findPetById(id));
    }
    @PutMapping("{id}")
    public ResponseEntity<PetResponse> updatePet(@Valid @RequestBody PetUpdateRequest request, @PathVariable Long id){
        return ResponseEntity.ok(petService.updatePet(request,id));
    }


}
