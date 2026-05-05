package com.dhruvil.Hotel.Reservation.System.controller;

import com.dhruvil.Hotel.Reservation.System.dto.AuthRequestDto;
import com.dhruvil.Hotel.Reservation.System.dto.AuthResponseDto;
import com.dhruvil.Hotel.Reservation.System.model.User;
import com.dhruvil.Hotel.Reservation.System.model.type.Role;
import com.dhruvil.Hotel.Reservation.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository repo;

    private final AuthenticationManager authManager;

    private final PasswordEncoder passwordEncoder;

    // Register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequestDto req) {
        if(repo.findByUsername(req.username).isPresent()) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(Role.USER);

        repo.save(user);

        return ResponseEntity.ok(new AuthResponseDto("User Registered"));
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto req) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            req.username,
                            req.password
                    )
            );

            return ResponseEntity.ok(new AuthResponseDto("Login successful"));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

}
