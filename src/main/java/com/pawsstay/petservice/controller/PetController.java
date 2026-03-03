package com.pawsstay.petservice.controller;

import com.pawsstay.petservice.dto.PetRequest;
import com.pawsstay.petservice.dto.PetResponse;
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
    public ResponseEntity<PetResponse> createPet(
            @RequestBody @Valid PetRequest pet,
            @RequestHeader("X-User-Id") String userId) {

        log.info("createPet:receive request form {} ", userId);

        PetResponse response = petService.createPet(pet, userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PetResponse>> getUserPets(@RequestHeader("X-User-Id") String userId) {
        log.info("getUserPets:receive request form {} ", userId);
        List<PetResponse> response = petService.getUserPets(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<PetResponse> getPetDetail(@PathVariable Long id, @RequestHeader("X-User-Id") String userId) {
        log.info("getPetDetail:receive request form {} query pet id {}", userId, id);
        PetResponse petDetail = petService.getPetDetail(id, userId);
        return ResponseEntity.ok(petDetail);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePet(@PathVariable Long id, @RequestHeader("X-User-Id") String userId) {
        log.info("deletePet:receive request form {} query pet id {}", userId, id);
        petService.deletePet(id, userId);
        return ResponseEntity.ok().build();
    }


}
