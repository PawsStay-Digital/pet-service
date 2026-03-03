package com.pawsstay.petservice.service;

import com.pawsstay.petservice.dto.PetRequest;
import com.pawsstay.petservice.dto.PetResponse;
import com.pawsstay.petservice.entity.Pet;
import com.pawsstay.petservice.exception.ResourceNotFoundException;
import com.pawsstay.petservice.exception.UnauthorizedException;
import com.pawsstay.petservice.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    @Override
    public PetResponse createPet(PetRequest request, String userId) {
        Pet pet = Pet.builder()
                .name(request.getName())
                .breed(request.getBreed())
                .age(request.getAge())
                .description(request.getDescription())
                .userId(userId)
                .build();
        Pet savedPet = petRepository.save(pet);
        log.info("Pet created with Id:{} by userId:{}", savedPet.getId(), userId);
        return mapToResponse(pet);
    }

    @Override
    public List<PetResponse> getUserPets(String userId) {
        return petRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public PetResponse getPetDetail(Long id, String userId) {
        Pet pet = petRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("can not find specific pet id"));

        return mapToResponse(pet);
    }

    @Override
    @Transactional
    public void deletePet(Long id, String userId) {
        if (!petRepository.existsByIdAndUserId(id, userId)) {
            throw new UnauthorizedException("unauthorize request");
        }
        petRepository.deleteById(id);

    }

    private PetResponse mapToResponse(Pet pet) {
        return PetResponse.builder()
                .id(pet.getId())
                .name(pet.getName())
                .breed(pet.getBreed())
                .age(pet.getAge())
                .description(pet.getDescription())
                .createdAt(pet.getCreatedAt())
                .build();

    }
}
