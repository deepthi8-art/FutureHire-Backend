package com.futurehire.dto.response;

public record AdminUserResponse(
        Long id,
        String fullName,
        String email,
        String department,
        String yearOfStudy,
        String cgpa,
        boolean enabled,
        String role
) {}