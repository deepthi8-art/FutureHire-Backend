package com.futurehire.controller;

import com.futurehire.dto.response.ApiResponse;
import com.futurehire.entity.InterviewExperience;
import com.futurehire.entity.User;
import com.futurehire.service.AuthService;
import com.futurehire.service.InterviewExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/interviews/experiences")
public class StudentExperienceController {

    @Autowired
    private InterviewExperienceService experienceService;

    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<InterviewExperience>>> getExperiences() {
        List<InterviewExperience> list = experienceService.getAllExperiences();
        return ResponseEntity.ok(ApiResponse.success("Interview experiences fetched", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InterviewExperience>> getExperienceById(@PathVariable Long id) {
        InterviewExperience exp = experienceService.getExperienceById(id);
        return ResponseEntity.ok(ApiResponse.success("Experience details fetched", exp));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<InterviewExperience>>> searchByCompany(@RequestParam(required = false) String company) {
        List<InterviewExperience> list = experienceService.searchByCompany(company);
        return ResponseEntity.ok(ApiResponse.success("Search results fetched", list));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<InterviewExperience>> shareExperience(@RequestBody InterviewExperience experience) {
        User student = authService.getCurrentAuthenticatedUser();
        InterviewExperience created = experienceService.shareExperience(student, experience);
        return ResponseEntity.ok(ApiResponse.success("Interview experience published", created));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteExperience(@PathVariable Long id) {
        User student = authService.getCurrentAuthenticatedUser();
        experienceService.deleteExperience(student, id);
        return ResponseEntity.ok(ApiResponse.success("Interview experience deleted", "Deleted"));
    }
}
