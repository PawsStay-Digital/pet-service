package com.pawsstay.pet_service.service;

import com.pawsstay.pet_service.dto.PetCreateRequest;
import com.pawsstay.pet_service.dto.PetResponse;
import com.pawsstay.pet_service.dto.PetUpdateRequest;
import com.pawsstay.pet_service.entity.Pet;
import com.pawsstay.pet_service.exception.ResourceNotFoundException;
import com.pawsstay.pet_service.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PetServiceImpl implements PetService{
    private final PetRepository petRepository;
    private final S3Service s3Service;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PetResponse createPet(PetCreateRequest pet, MultipartFile image) {
        String photoUrl = null;
        if (image != null && !image.isEmpty()) {
            try{
                photoUrl = s3Service.uploadFile(image);
            }catch (Exception e) {
                log.error("S3 Upload failed for pet: {}. Error: {}", pet.getName(), e.getMessage());
            }
        }
        Pet petDb = convertPet(pet, photoUrl);
        petDb = petRepository.save(petDb);

        return convertPetResponse(petDb);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PetResponse updatePet(PetUpdateRequest req, Long id) {
        Pet pet = petRepository.findPetById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + id));
        pet.setName(req.getName());
        pet.setVaccinated(req.isVaccinated());
        pet.setNeutered(req.isNeutered());
        pet.setTrained(req.isTrained());
        pet.setNotes(req.getNotes());
        Pet petSaved = petRepository.save(pet);

        return convertPetResponse(petSaved);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PetResponse> findPetsByOwner(Long ownerId) {
        return petRepository.findPetsByOwnerId(ownerId).stream().map(this::convertPetResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PetResponse findPetById(Long id) {
        return petRepository.findPetById(id).map(this::convertPetResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + id));
    }

    private Pet convertPet(PetCreateRequest pet, String photoUrl){
        return Pet.builder().ownerId(pet.getOwnerId())
                .name(pet.getName())
                .breed(pet.getBreed())
                .birthday(pet.getBirthday())
                .gender(pet.getGender())
                .chipNumber(pet.getChipNumber())
                .isVaccinated(pet.isVaccinated())
                .isNeutered(pet.isNeutered())
                .isTrained(pet.isTrained())
                .notes(pet.getNotes())
                .photoUrl(photoUrl).build();
    }
    private PetResponse convertPetResponse(Pet pet){
        return PetResponse.builder().id(pet.getId())
                .ownerId(pet.getOwnerId())
                .name(pet.getName())
                .breed(pet.getBreed())
                .birthday(pet.getBirthday())
                .gender(pet.getGender())
                .chipNumber(pet.getChipNumber())
                .isVaccinated(pet.isVaccinated())
                .isNeutered(pet.isNeutered())
                .isTrained(pet.isTrained())
                .notes(pet.getNotes())
                .photoUrl(pet.getPhotoUrl()).build();

    }
}
