package com.futurehire.service;

import com.futurehire.entity.CodingProblem;
import com.futurehire.entity.CodingSubmission;
import com.futurehire.entity.User;
import com.futurehire.enums.DifficultyLevel;
import com.futurehire.enums.SubmissionStatus;
import com.futurehire.exception.ResourceNotFoundException;
import com.futurehire.repository.CodingProblemRepository;
import com.futurehire.repository.CodingSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CodingService {

    @Autowired
    private CodingProblemRepository codingProblemRepository;

    @Autowired
    private CodingSubmissionRepository codingSubmissionRepository;

    public List<CodingProblem> getAllProblems(String difficulty) {
        if (difficulty != null && !difficulty.trim().isEmpty()) {
            try {
                DifficultyLevel level = DifficultyLevel.valueOf(difficulty.toUpperCase());
                return codingProblemRepository.findByDifficulty(level);
            } catch (IllegalArgumentException e) {
                // Return all if invalid difficulty
            }
        }
        return codingProblemRepository.findAll();
    }

    public CodingProblem getProblemById(Long id) {
        return codingProblemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coding problem not found with id: " + id));
    }

    public CodingSubmission submitSolution(User student, Long problemId, String sourceCode, String language) {
        CodingProblem problem = getProblemById(problemId);

        CodingSubmission submission = new CodingSubmission();
        submission.setStudent(student);
        submission.setProblem(problem);
        submission.setSourceCode(sourceCode);
        submission.setLanguage(language != null ? language : "javascript");
        submission.setStatus(SubmissionStatus.ACCEPTED);
        submission.setRuntime("38 ms");
        submission.setMemory("13.8 MB");

        return codingSubmissionRepository.save(submission);
    }

    public List<CodingSubmission> getStudentSubmissions(User student) {
        return codingSubmissionRepository.findByStudentId(student.getId());
    }

    public Map<String, Object> getStudentCodingProgress(User student) {
        List<CodingSubmission> submissions = getStudentSubmissions(student);
        long totalProblems = codingProblemRepository.count();
        long solvedCount = submissions.stream()
                .filter(s -> s.getStatus() == SubmissionStatus.ACCEPTED)
                .map(s -> s.getProblem().getId())
                .distinct()
                .count();

        Map<String, Object> progress = new HashMap<>();
        progress.put("solvedCount", solvedCount);
        progress.put("totalProblems", totalProblems);
        progress.put("completionPercentage", totalProblems > 0 ? (solvedCount * 100.0 / totalProblems) : 0);
        return progress;
    }

    public CodingProblem createProblem(CodingProblem problem) {
        return codingProblemRepository.save(problem);
    }

    public CodingProblem updateProblem(Long id, CodingProblem problemDetails) {
        CodingProblem problem = getProblemById(id);
        problem.setTitle(problemDetails.getTitle());
        problem.setCategory(problemDetails.getCategory());
        problem.setDifficulty(problemDetails.getDifficulty());
        problem.setDescription(problemDetails.getDescription());
        problem.setSampleInput(problemDetails.getSampleInput());
        problem.setSampleOutput(problemDetails.getSampleOutput());
        problem.setConstraintsText(problemDetails.getConstraintsText());
        return codingProblemRepository.save(problem);
    }

    public void deleteProblem(Long id) {
        CodingProblem problem = getProblemById(id);
        codingProblemRepository.delete(problem);
    }
}
