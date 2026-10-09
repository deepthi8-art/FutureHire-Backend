package com.futurehire.controller;

import com.futurehire.dto.response.ApiResponse;
import com.futurehire.entity.AptitudeQuiz;
import com.futurehire.entity.CodingProblem;
import com.futurehire.service.AptitudeService;
import com.futurehire.service.CodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/questions")
@PreAuthorize("hasRole('ADMIN')")
public class AdminQuestionsController {

    @Autowired
    private CodingService codingService;

    @Autowired
    private AptitudeService aptitudeService;

    @PostMapping("/coding")
    public ResponseEntity<ApiResponse<CodingProblem>> addCodingQuestion(@RequestBody CodingProblem problem) {
        CodingProblem created = codingService.createProblem(problem);
        return ResponseEntity.ok(ApiResponse.success("Coding question created", created));
    }

    @GetMapping("/coding")
    public ResponseEntity<ApiResponse<List<CodingProblem>>> getCodingQuestions() {
        List<CodingProblem> list = codingService.getAllProblems(null);
        return ResponseEntity.ok(ApiResponse.success("Coding questions fetched", list));
    }

    @PutMapping("/coding/{id}")
    public ResponseEntity<ApiResponse<CodingProblem>> updateCodingQuestion(@PathVariable Long id, @RequestBody CodingProblem details) {
        CodingProblem updated = codingService.updateProblem(id, details);
        return ResponseEntity.ok(ApiResponse.success("Coding question updated", updated));
    }

    @DeleteMapping("/coding/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCodingQuestion(@PathVariable Long id) {
        codingService.deleteProblem(id);
        return ResponseEntity.ok(ApiResponse.success("Coding problem deleted", "Deleted"));
    }

    @PostMapping("/aptitude")
    public ResponseEntity<ApiResponse<AptitudeQuiz>> addAptitudeQuiz(@RequestBody AptitudeQuiz quiz) {
        AptitudeQuiz created = aptitudeService.createQuiz(quiz);
        return ResponseEntity.ok(ApiResponse.success("Aptitude quiz created", created));
    }

    @GetMapping("/aptitude")
    public ResponseEntity<ApiResponse<List<AptitudeQuiz>>> getAptitudeQuizzes() {
        List<AptitudeQuiz> list = aptitudeService.getAllQuizzes();
        return ResponseEntity.ok(ApiResponse.success("Aptitude quizzes fetched", list));
    }
}
