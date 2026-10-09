package com.futurehire.controller;

import com.futurehire.dto.response.ApiResponse;
import com.futurehire.entity.Resume;
import com.futurehire.entity.User;
import com.futurehire.service.AuthService;
import com.futurehire.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/student/resumes")
public class StudentResumeController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private AuthService authService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<Resume>> uploadResume(@RequestParam("file") MultipartFile file) throws IOException {
        User student = authService.getCurrentAuthenticatedUser();
        Resume resume = resumeService.uploadResume(student, file);
        return ResponseEntity.ok(ApiResponse.success("Resume uploaded and scanned successfully", resume));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Resume>> getResumeDetails() {
        User student = authService.getCurrentAuthenticatedUser();
        Resume resume = resumeService.getStudentResume(student);
        return ResponseEntity.ok(ApiResponse.success("Resume details fetched", resume));
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadResume() {
        User student = authService.getCurrentAuthenticatedUser();
        Resume resume = resumeService.getStudentResume(student);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resume.getFileName() + "\"")
                .body(resume.getFileData());
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<String>> deleteResume() {
        User student = authService.getCurrentAuthenticatedUser();
        resumeService.deleteStudentResume(student);
        return ResponseEntity.ok(ApiResponse.success("Resume deleted successfully", "Deleted"));
    }
}
