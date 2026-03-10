package com.pawsstay.petservice.config;


import com.pawsstay.petservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(
        name = "identity-service",
        fallback = UserClientFallback.class
)
public interface UserClient {
    @GetMapping("/api/user/{userId}")
    UserDTO getUserById(@PathVariable String userId);
}
