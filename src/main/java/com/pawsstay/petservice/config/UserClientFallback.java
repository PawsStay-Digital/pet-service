package com.pawsstay.petservice.config;

import com.pawsstay.petservice.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {
    @Override
    public UserDTO getUserById(String userId) {
        return UserDTO.builder()
                .userId(userId)
                .username("User Info Currently Unavailable")
                .email("N/A").build();
    }
}
