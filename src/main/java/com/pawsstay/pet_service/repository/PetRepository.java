package com.pawsstay.pet_service.repository;

import com.pawsstay.pet_service.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet,Long> {
    Collection<Pet> findPetsByOwnerId(Long ownerId);
    Optional<Pet> findPetById(Long id);

}
