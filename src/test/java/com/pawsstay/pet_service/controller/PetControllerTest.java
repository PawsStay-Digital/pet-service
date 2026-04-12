package com.pawsstay.pet_service.controller;

import com.pawsstay.pet_service.service.PetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PetController.class)
public class PetControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private PetService petService;

    @Test
    public void testHealthCheck() throws Exception {
        mockMvc.perform(get("/api/v1/pets/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Pet Service is running on Virtual Threads!"));
    }
}
