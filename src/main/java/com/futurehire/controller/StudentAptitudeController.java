package com.futurehire.controller;

import com.futurehire.dto.response.ApiResponse;
import com.futurehire.entity.AptitudeQuiz;
import com.futurehire.entity.QuizResult;
import com.futurehire.entity.User;
import com.futurehire.service.AptitudeService;
import com.futurehire.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student/aptitude")
public class StudentAptitudeController {

    @Autowired
    private AptitudeService aptitudeService;

    @Autowired
    private AuthService authService;

    @GetMapping("/quizzes")
    public ResponseEntity<ApiResponse<List<AptitudeQuiz>>> getQuizzes() {
        List<AptitudeQuiz> quizzes = aptitudeService.getAllQuizzes();
        return ResponseEntity.ok(ApiResponse.success("Available quizzes fetched", quizzes));
    }

    @GetMapping("/quizzes/{id}")
    public ResponseEntity<ApiResponse<AptitudeQuiz>> getQuizDetails(@PathVariable Long id) {
        AptitudeQuiz quiz = aptitudeService.getQuizById(id);
        return ResponseEntity.ok(ApiResponse.success("Quiz details fetched", quiz));
    }

    @GetMapping("/quizzes/{id}/questions")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getQuizQuestions(@PathVariable Long id) {
        List<Map<String, Object>> questions = aptitudeService.getQuizQuestionsForStudent(id);
        return ResponseEntity.ok(ApiResponse.success("Quiz questions fetched without answers", questions));
    }

    @PostMapping("/quizzes/{id}/submit")
    public ResponseEntity<ApiResponse<QuizResult>> submitQuiz(@PathVariable Long id, @RequestBody Map<String, Integer> answersMap) {
        User student = authService.getCurrentAuthenticatedUser();
        Map<Long, Integer> studentAnswers = new HashMap<>();
        answersMap.forEach((qIdStr, optIdx) -> studentAnswers.put(Long.parseLong(qIdStr), optIdx));

        QuizResult result = aptitudeService.submitQuizAnswers(student, id, studentAnswers);
        return ResponseEntity.ok(ApiResponse.success("Quiz submitted successfully", result));
    }

    @GetMapping("/results")
    public ResponseEntity<ApiResponse<List<QuizResult>>> getResults() {
        User student = authService.getCurrentAuthenticatedUser();
        List<QuizResult> results = aptitudeService.getStudentResults(student);
        return ResponseEntity.ok(ApiResponse.success("Student quiz results fetched", results));
    }
}
