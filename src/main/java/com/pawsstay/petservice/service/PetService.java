package com.pawsstay.petservice.service;

import com.pawsstay.petservice.dto.PetDetailResponse;
import com.pawsstay.petservice.dto.PetRequest;
import com.pawsstay.petservice.dto.PetDTO;

import java.util.List;

public interface PetService {
    PetDTO createPet(PetRequest request, String userId);

    List<PetDTO> getUserPets(String userId);

    PetDetailResponse getPetDetail(Long id, String userId);

    void deletePet(Long id, String userId);
}
