package com.pysarivka.WeekEnds.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pysarivka.WeekEnds.domain.User;
import com.pysarivka.WeekEnds.dto.AuthRequestDTO;
import com.pysarivka.WeekEnds.dto.AuthResponseDTO;
import com.pysarivka.WeekEnds.security.JwtService;
import com.pysarivka.WeekEnds.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/auth") // Чиста адреса без /api
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private JwtService jwtService;

    // Перший вхід: перевірка паролю та повернення токену для авто-входу
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        logger.info("REST request to login user: {}", request.getUsername());
        
        // Spring Security сам звірить пароль із бази (захешований BCrypt)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Якщо все ок, дістаємо користувача та виписуємо йому токен
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponseDTO(jwtToken, request.getUsername()));
    }
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof UserDetails)) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        
        String email = ((UserDetails) principal).getUsername();
        java.util.Optional<User> userOptional = userServiceImpl.findByEmail(email);
        
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(404).body("User not found");
        }

        User user = userOptional.get();
        // Повертаємо повноцінний JSON із даними профілю
        return ResponseEntity.ok(new com.pysarivka.WeekEnds.dto.UserProfileDTO(
                user.getEmail(), user.getFirstname(), user.getLastname()
        ));
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequestDTO request) {
        logger.info("REST request to register user: {} {} ({})", 
                request.getFirstname(), request.getLastname(), request.getUsername());
        
        if (userServiceImpl.findByEmail(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Email is already taken");
        }

        User newUser = new User();
        newUser.setEmail(request.getUsername());
        newUser.setPassword(request.getPassword()); // Передаємо чистий пароль для сервісу
        newUser.setFirstname(request.getFirstname()); // Записуємо ім'я
        newUser.setLastname(request.getLastname());   // Записуємо прізвище

        userServiceImpl.saveUser(newUser);
        return ResponseEntity.ok("User registered successfully");
    }
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<String> updateProfile(@RequestBody com.pysarivka.WeekEnds.dto.UpdateProfileDTO request) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof UserDetails)) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        
        String currentUsername = ((UserDetails) principal).getUsername();
        logger.info("REST request to update profile for user: {}", currentUsername);
        java.util.Optional<User> userOptional = userServiceImpl.findByEmail(currentUsername);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(404).body("User not found");
        }

        User user = userOptional.get();
        
        if (request.getFirstname() != null && !request.getFirstname().trim().isEmpty()) {
            user.setFirstname(request.getFirstname());
        }
        if (request.getLastname() != null && !request.getLastname().trim().isEmpty()) {
            user.setLastname(request.getLastname());
        }

        userServiceImpl.updateUser(user); 
        return ResponseEntity.ok("Profile updated successfully");
    }
}
