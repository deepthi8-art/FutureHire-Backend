package com.futurehire.controller;

import com.futurehire.dto.response.ApiResponse;
import com.futurehire.entity.CodingProblem;
import com.futurehire.entity.CodingSubmission;
import com.futurehire.entity.User;
import com.futurehire.service.AuthService;
import com.futurehire.service.CodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student/coding")
public class StudentCodingController {

    @Autowired
    private CodingService codingService;

    @Autowired
    private AuthService authService;

    @GetMapping("/problems")
    public ResponseEntity<ApiResponse<List<CodingProblem>>> getProblems(@RequestParam(required = false) String difficulty) {
        List<CodingProblem> problems = codingService.getAllProblems(difficulty);
        return ResponseEntity.ok(ApiResponse.success("Coding problems fetched", problems));
    }

    @GetMapping("/problems/{id}")
    public ResponseEntity<ApiResponse<CodingProblem>> getProblemDetails(@PathVariable Long id) {
        CodingProblem problem = codingService.getProblemById(id);
        return ResponseEntity.ok(ApiResponse.success("Problem details fetched", problem));
    }

    @PostMapping("/submissions")
    public ResponseEntity<ApiResponse<CodingSubmission>> submitSolution(@RequestBody Map<String, String> request) {
        User student = authService.getCurrentAuthenticatedUser();
        Long problemId = Long.parseLong(request.get("problemId"));
        String sourceCode = request.get("sourceCode");
        String language = request.get("language");

        CodingSubmission submission = codingService.submitSolution(student, problemId, sourceCode, language);
        return ResponseEntity.ok(ApiResponse.success("Solution submitted successfully", submission));
    }

    @GetMapping("/submissions")
    public ResponseEntity<ApiResponse<List<CodingSubmission>>> getSubmissions() {
        User student = authService.getCurrentAuthenticatedUser();
        List<CodingSubmission> submissions = codingService.getStudentSubmissions(student);
        return ResponseEntity.ok(ApiResponse.success("Student submissions fetched", submissions));
    }

    @GetMapping("/progress")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getProgress() {
        User student = authService.getCurrentAuthenticatedUser();
        Map<String, Object> progress = codingService.getStudentCodingProgress(student);
        return ResponseEntity.ok(ApiResponse.success("Coding progress fetched", progress));
    }
}
