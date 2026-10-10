package com.futurehire.controller;

import com.futurehire.dto.response.ApiResponse;
import com.futurehire.dto.response.AdminUserResponse;
import com.futurehire.entity.StudentProfile;
import com.futurehire.entity.User;
import com.futurehire.enums.RoleType;
import com.futurehire.repository.StudentProfileRepository;
import com.futurehire.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUsersController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AdminUserResponse>>> getAllUsers() {
        List<User> users = userRepository.findAll().stream()
            .filter(user -> user.getRole().getName() == RoleType.ROLE_STUDENT)
            .toList();
        Map<Long, StudentProfile> profilesByUserId = studentProfileRepository.findAll().stream()
                .collect(Collectors.toMap(profile -> profile.getUser().getId(), Function.identity()));
        List<AdminUserResponse> response = users.stream().map(user -> {
            StudentProfile profile = profilesByUserId.get(user.getId());
            return new AdminUserResponse(
                    user.getId(),
                    user.getFullName(),
                    user.getEmail(),
                    profile == null ? null : profile.getDepartment(),
                    profile == null ? null : profile.getYearOfStudy(),
                    profile == null ? null : profile.getCgpa(),
                    user.isEnabled(),
                    user.getRole().getName().name()
            );
        }).toList();
        return ResponseEntity.ok(ApiResponse.success("All registered users fetched", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return ResponseEntity.ok(ApiResponse.success("User details fetched", user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok(ApiResponse.success("User deleted successfully", "Deleted"));
    }
}
