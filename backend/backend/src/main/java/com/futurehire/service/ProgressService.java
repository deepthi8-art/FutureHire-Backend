package com.futurehire.service;

import com.futurehire.entity.User;
import com.futurehire.repository.CodingSubmissionRepository;
import com.futurehire.repository.MockInterviewBookingRepository;
import com.futurehire.repository.QuizResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProgressService {

    @Autowired
    private CodingSubmissionRepository codingSubmissionRepository;

    @Autowired
    private QuizResultRepository quizResultRepository;

    @Autowired
    private MockInterviewBookingRepository bookingRepository;

    @Autowired
    private com.futurehire.repository.CodingProblemRepository codingProblemRepository;

    @Autowired
    private com.futurehire.repository.ResumeRepository resumeRepository;

    public Map<String, Object> getOverallStudentProgress(User student) {
        Map<String, Object> map = new HashMap<>();

        long codingSolved = codingSubmissionRepository.findByStudentId(student.getId()).stream()
                .filter(s -> s.getStatus().name().equals("ACCEPTED"))
                .map(s -> s.getProblem().getId())
                .distinct().count();

        long totalProblems = codingProblemRepository.count();
        if (totalProblems == 0) totalProblems = 5;

        var quizResults = quizResultRepository.findByStudentId(student.getId());
        double avgAptitude = quizResults.stream().mapToDouble(q -> q.getPercentage()).average().orElse(0.0);

        long mockCount = bookingRepository.findByStudentId(student.getId()).size();
        boolean hasResume = resumeRepository.findByStudentId(student.getId()).isPresent();

        double codingFactor = Math.min(1.0, (double) codingSolved / Math.max(1, totalProblems));
        double aptitudeFactor = quizResults.isEmpty() ? 0.6 : (avgAptitude / 100.0);
        double mockFactor = Math.min(1.0, mockCount / 3.0);
        double resumeFactor = hasResume ? 1.0 : 0.4;

        int readinessScore = (int) Math.round((codingFactor * 35.0) + (aptitudeFactor * 30.0) + (resumeFactor * 20.0) + (mockFactor * 15.0));
        readinessScore = Math.max(20, Math.min(100, readinessScore));

        map.put("readinessScore", readinessScore);
        map.put("codingSolved", codingSolved);
        map.put("totalCodingProblems", totalProblems);
        map.put("aptitudeAvgScore", avgAptitude > 0 ? (Math.round(avgAptitude * 10.0) / 10.0) : 80.0);
        map.put("mockInterviewsCompleted", mockCount);

        List<Map<String, Object>> chartData = new ArrayList<>();
        chartData.add(Map.of("month", "May", "codingSolved", 25, "aptitudeScore", 65));
        chartData.add(Map.of("month", "Jun", "codingSolved", 48, "aptitudeScore", 72));
        chartData.add(Map.of("month", "Jul", "codingSolved", 80, "aptitudeScore", 78));
        chartData.add(Map.of("month", "Aug", "codingSolved", 110, "aptitudeScore", 84));
        chartData.add(Map.of("month", "Sep", "codingSolved", 142, "aptitudeScore", 88));

        map.put("chartData", chartData);
        return map;
    }
}
