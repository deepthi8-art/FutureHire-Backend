package com.futurehire.service;

import com.futurehire.dto.request.AuthRequest;
import com.futurehire.dto.request.RegisterRequest;
import com.futurehire.dto.response.AuthResponse;
import com.futurehire.entity.Role;
import com.futurehire.entity.StudentProfile;
import com.futurehire.entity.User;
import com.futurehire.enums.RoleType;
import com.futurehire.exception.BadRequestException;
import com.futurehire.exception.ResourceNotFoundException;
import com.futurehire.repository.RoleRepository;
import com.futurehire.repository.StudentProfileRepository;
import com.futurehire.repository.UserRepository;
import com.futurehire.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Transactional
    public AuthResponse registerStudent(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new BadRequestException("Email address is already registered: " + registerRequest.getEmail());
        }

        Role studentRole = roleRepository.findByName(RoleType.ROLE_STUDENT)
                .orElseGet(() -> roleRepository.save(new Role(RoleType.ROLE_STUDENT)));

        User user = new User(
                registerRequest.getFullName(),
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()),
                studentRole
        );

        User savedUser = userRepository.save(user);

        StudentProfile profile = new StudentProfile(
                savedUser,
                registerRequest.getCollegeName(),
                registerRequest.getDepartment(),
                registerRequest.getYearOfStudy(),
                registerRequest.getPhone()
        );
        studentProfileRepository.save(profile);

        String token = tokenProvider.generateTokenFromEmail(savedUser.getEmail());

        return new AuthResponse(
                token,
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                "ROLE_STUDENT"
        );
    }

    public AuthResponse login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + authRequest.getEmail()));

        String token = tokenProvider.generateToken(authentication);

        return new AuthResponse(
                token,
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole().getName().name()
        );
    }

    public User getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BadRequestException("User is not authenticated");
        }
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Authenticated user not found"));
    }
}
