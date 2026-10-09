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

    public Map<String, Object> getAdminOverviewReports() {
        Map<String, Object> report = new HashMap<>();

        report.put("totalRegisteredStudents", userRepository.count());
        report.put("totalCodingProblems", codingProblemRepository.count());
        report.put("totalCodingSubmissions", codingSubmissionRepository.count());
        report.put("totalAptitudeQuizzes", aptitudeQuizRepository.count());
        report.put("totalCompanies", companyRepository.count());
        report.put("totalMockSlots", mockInterviewSlotRepository.count());

        List<Map<String, Object>> deptDist = new ArrayList<>();
        deptDist.add(Map.of("name", "Computer Science (CSE)", "students", 450));
        deptDist.add(Map.of("name", "Information Tech (IT)", "students", 320));
        deptDist.add(Map.of("name", "Electronics (ECE)", "students", 280));
        deptDist.add(Map.of("name", "Electrical (EEE)", "students", 190));
        deptDist.add(Map.of("name", "Mechanical & Civil", "students", 140));

        report.put("departmentDistribution", deptDist);
        return report;
    }
}
