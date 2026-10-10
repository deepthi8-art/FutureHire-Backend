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

    public Map<String, Object> getOverallStudentProgress(User student) {
        Map<String, Object> map = new HashMap<>();

        long codingSolved = codingSubmissionRepository.findByStudentId(student.getId()).stream()
                .filter(s -> s.getStatus().name().equals("ACCEPTED"))
                .map(s -> s.getProblem().getId())
                .distinct().count();

        var quizResults = quizResultRepository.findByStudentId(student.getId());
        double avgAptitude = quizResults.stream().mapToDouble(q -> q.getPercentage()).average().orElse(85.0);

        long mockCount = bookingRepository.findByStudentId(student.getId()).size();

        map.put("readinessScore", 85);
        map.put("codingSolved", codingSolved);
        map.put("totalCodingProblems", 250);
        map.put("aptitudeAvgScore", Math.round(avgAptitude * 10.0) / 10.0);
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
