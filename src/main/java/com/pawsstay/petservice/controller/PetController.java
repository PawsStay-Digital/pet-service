package com.pawsstay.petservice.controller;

import com.pawsstay.petservice.entity.Pet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/pets")
public class PetController {
    @PostMapping
    public ResponseEntity<String> createPet(
            @RequestBody Pet pet,
            @RequestHeader("X-User-Email") String ownerEmail) {

        // 現在你已經拿到使用者的 Email 了！
        System.out.println("收到來自使用者 " + ownerEmail + " 的寵物建立請求");

        pet.setOwnerEmail(ownerEmail);
        // 執行儲存邏輯...
        return ResponseEntity.ok("Pet created for " + ownerEmail);
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, String>> testHeader(
            @RequestHeader(value = "X-User-Email", required = false) String email) {

        Map<String, String> response = new HashMap<>();
        response.put("receivedEmail", email != null ? email : "No Email Found");
        response.put("status", "Success from Pet Service");

        return ResponseEntity.ok(response);
    }
}
