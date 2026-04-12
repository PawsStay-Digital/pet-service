package com.pawsstay.pet_service.service;

import com.pawsstay.pet_service.dto.PetCreateRequest;
import com.pawsstay.pet_service.dto.PetResponse;
import com.pawsstay.pet_service.dto.PetUpdateRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface PetService {
    PetResponse createPet(PetCreateRequest pet, MultipartFile image);
    PetResponse updatePet(PetUpdateRequest pet, Long id);
    Collection<PetResponse> findPetsByOwner(Long ownerId);
    PetResponse findPetById(Long id);

}
