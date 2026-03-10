package com.pawsstay.petservice.service;

import com.pawsstay.petservice.config.UserClient;
import com.pawsstay.petservice.dto.*;
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
    private final UserClient userClient;

    @Override
    public PetDTO createPet(PetRequest request, String userId) {
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
    public List<PetDTO> getUserPets(String userId) {
        return petRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public PetDetailResponse getPetDetail(Long id, String userId) {
        Pet pet = petRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("can not find specific pet id"));

        PetDTO petDTO = mapToResponse(pet);
        UserDTO userDTO = userClient.getUserById(userId);
        OwnerDTO ownerDTO = OwnerDTO.builder().ownerId(userDTO.getUserId())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail()).build();
        return new PetDetailResponse(petDTO,ownerDTO);
    }

    @Override
    @Transactional
    public void deletePet(Long id, String userId) {
        if (!petRepository.existsByIdAndUserId(id, userId)) {
            throw new UnauthorizedException("unauthorize request");
        }
        petRepository.deleteById(id);

    }

    private PetDTO mapToResponse(Pet pet) {
        return PetDTO.builder()
                .id(pet.getId())
                .name(pet.getName())
                .breed(pet.getBreed())
                .age(pet.getAge())
                .description(pet.getDescription())
                .createdAt(pet.getCreatedAt())
                .build();

    }
}
