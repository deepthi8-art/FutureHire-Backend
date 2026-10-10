package com.futurehire.controller;

import com.futurehire.dto.response.ApiResponse;
import com.futurehire.entity.User;
import com.futurehire.service.AuthService;
import com.futurehire.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/student/progress")
public class StudentProgressController {

    @Autowired
    private ProgressService progressService;

    @Autowired
    private AuthService authService;

    @GetMapping("/overview")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getOverview() {
        User student = authService.getCurrentAuthenticatedUser();
        Map<String, Object> progress = progressService.getOverallStudentProgress(student);
        return ResponseEntity.ok(ApiResponse.success("Overall student progress fetched", progress));
    }
}
