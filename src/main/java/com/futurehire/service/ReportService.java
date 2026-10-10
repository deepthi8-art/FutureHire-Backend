package com.futurehire.service;

import com.futurehire.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CodingProblemRepository codingProblemRepository;

    @Autowired
    private CodingSubmissionRepository codingSubmissionRepository;

    @Autowired
    private AptitudeQuizRepository aptitudeQuizRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private MockInterviewSlotRepository mockInterviewSlotRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    public Map<String, Object> getAdminOverviewReports() {
        Map<String, Object> report = new HashMap<>();

        long studentCount = userRepository.findAll().stream()
            .filter(user -> user.getRole().getName().name().equals("ROLE_STUDENT"))
            .count();
        report.put("totalRegisteredStudents", studentCount);
        report.put("totalCodingProblems", codingProblemRepository.count());
        report.put("totalCodingSubmissions", codingSubmissionRepository.count());
        report.put("totalAptitudeQuizzes", aptitudeQuizRepository.count());
        report.put("totalCompanies", companyRepository.count());
        report.put("totalMockSlots", mockInterviewSlotRepository.count());

        Map<String, Long> departmentCounts = studentProfileRepository.findAll().stream()
            .collect(java.util.stream.Collectors.groupingBy(
                profile -> profile.getDepartment() == null || profile.getDepartment().isBlank()
                    ? "Unspecified" : profile.getDepartment(),
                TreeMap::new,
                java.util.stream.Collectors.counting()));
        List<Map<String, Object>> deptDist = departmentCounts.entrySet().stream()
            .map(entry -> Map.<String, Object>of("name", entry.getKey(), "students", entry.getValue()))
            .toList();

        report.put("departmentDistribution", deptDist);
        return report;
    }
}
