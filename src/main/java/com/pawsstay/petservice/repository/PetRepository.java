package com.pawsstay.petservice.repository;

import com.pawsstay.petservice.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet,Long> {
    //for step 1 list all pets api
    List<Pet> findByOwnerEmail(String ownerEmail);
    Optional<Pet> findByIdAndOwnerEmail(Long id, String ownerEmail);
//    //for step 2 list all pets and pageable
//    Page<Pet> findByOwnerEmail(String ownerEmail, Pageable pageable);
    boolean existsByIdAndOwnerEmail(Long id, String ownerEmail);
//    List<Pet> findByOwnerEmailAndBreed(String ownerEmail, String breed);
}
