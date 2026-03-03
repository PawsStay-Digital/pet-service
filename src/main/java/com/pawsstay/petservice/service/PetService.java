package com.pawsstay.petservice.service;

import com.pawsstay.petservice.dto.PetRequest;
import com.pawsstay.petservice.dto.PetResponse;

import java.util.List;

public interface PetService {
    PetResponse createPet(PetRequest request, String userId);

    List<PetResponse> getUserPets(String userId);

    PetResponse getPetDetail(Long id, String userId);

    void deletePet(Long id, String userId);
}
