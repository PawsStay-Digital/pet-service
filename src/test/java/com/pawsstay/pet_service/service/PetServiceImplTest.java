package com.pawsstay.pet_service.service;

import com.pawsstay.pet_service.dto.PetCreateRequest;
import com.pawsstay.pet_service.dto.PetResponse;
import com.pawsstay.pet_service.entity.Pet;
import com.pawsstay.pet_service.repository.PetRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.AssertionsKt.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PetServiceImplTest {
    @Mock
    private PetRepository petRepository;
    @InjectMocks
    private PetServiceImpl petService;
    @Test
    @DisplayName("createPet_Success")
    void createPet_Success(){
        PetCreateRequest request = PetCreateRequest.builder().ownerId(1L)
                .name("Button").breed("Bichon").birthday(LocalDate.of(2000, 3, 5))
                .gender("Female").isVaccinated(true).isNeutered(true).isTrained(true).chipNumber("abc123")
                .notes("test notes").build();

        Pet expectPet = Pet.builder().ownerId(1L).id(1L)
                .name("Button").breed("Bichon").birthday(LocalDate.of(2000, 3, 5))
                .gender("Female").isVaccinated(true).isNeutered(true).isTrained(true).chipNumber("abc123")
                .notes("test notes").build();

        when(petRepository.save(any(Pet.class))).thenReturn(expectPet);
        PetResponse result = petService.createPet(request, null);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getOwnerId());
        assertEquals("Button", result.getName());
        assertEquals("Bichon", result.getBreed());
        verify(petRepository, times(1)).save(any(Pet.class));
    }
}
