package com.pawsstay.petservice.controller;

import com.pawsstay.petservice.dto.PetDetailResponse;
import com.pawsstay.petservice.dto.PetRequest;
import com.pawsstay.petservice.dto.PetDTO;
import com.pawsstay.petservice.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pets")
@Slf4j
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    public ResponseEntity<PetDTO> createPet(
            @RequestBody @Valid PetRequest pet,
            @RequestHeader("X-User-Id") String userId) {

        log.info("createPet:receive request form {} ", userId);

        PetDTO response = petService.createPet(pet, userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PetDTO>> getUserPets(@RequestHeader("X-User-Id") String userId) {
        log.info("getUserPets:receive request form {} ", userId);
        List<PetDTO> response = petService.getUserPets(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<PetDetailResponse> getPetDetail(@PathVariable Long id, @RequestHeader("X-User-Id") String userId) {
        log.info("getPetDetail:receive request form {} query pet id {}", userId, id);
        PetDetailResponse petDetail = petService.getPetDetail(id, userId);
        return ResponseEntity.ok(petDetail);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePet(@PathVariable Long id, @RequestHeader("X-User-Id") String userId) {
        log.info("deletePet:receive request form {} query pet id {}", userId, id);
        petService.deletePet(id, userId);
        return ResponseEntity.ok().build();
    }


}
