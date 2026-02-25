package com.pawsstay.petservice.service;

import com.pawsstay.petservice.dto.PetRequest;
import com.pawsstay.petservice.dto.PetResponse;

import java.util.List;

public interface PetService {
    PetResponse createPet(PetRequest request, String email);
    List<PetResponse> getUserPets(String email);
    PetResponse getPetDetail(Long id, String email);
    void deletePet(Long id, String email);
}
