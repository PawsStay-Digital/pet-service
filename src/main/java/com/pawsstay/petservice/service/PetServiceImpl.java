package com.pawsstay.petservice.service;

import com.pawsstay.petservice.dto.PetRequest;
import com.pawsstay.petservice.dto.PetResponse;
import com.pawsstay.petservice.entity.Pet;
import com.pawsstay.petservice.exception.ResourceNotFoundException;
import com.pawsstay.petservice.exception.UnauthorizedException;
import com.pawsstay.petservice.repository.PetRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class PetServiceImpl implements PetService{
    private final PetRepository petRepository;
    @Override
    public PetResponse createPet(PetRequest request, String email) {
       Pet pet = Pet.builder()
               .name(request.getName())
               .breed(request.getBreed())
               .age(request.getAge())
               .description(request.getDescription())
               .ownerEmail(email)
               .build();
       Pet savedPet = petRepository.save(pet);
       log.info("Pet created with Id:{} by user:{}",savedPet.getId(),email);
       return mapToResponse(pet);
    }

    @Override
    public List<PetResponse> getUserPets(String email) {
        return petRepository.findByOwnerEmail(email)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public PetResponse getPetDetail(Long id, String email) {
        Pet pet = petRepository.findByIdAndOwnerEmail(id, email)
                .orElseThrow(() -> new ResourceNotFoundException("can not find specific pet id"));

        return mapToResponse(pet);
    }

    @Override
    @Transactional
    public void deletePet(Long id, String email) {
        if (!petRepository.existsByIdAndOwnerEmail(id, email)) {
            throw new UnauthorizedException("unauthorize request");
        }
        petRepository.deleteById(id);

    }

    private PetResponse mapToResponse(Pet pet){
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
