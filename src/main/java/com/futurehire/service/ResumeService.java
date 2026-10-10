package com.futurehire.service;

import com.futurehire.entity.Resume;
import com.futurehire.entity.User;
import com.futurehire.exception.BadRequestException;
import com.futurehire.exception.ResourceNotFoundException;
import com.futurehire.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Transactional
    public Resume uploadResume(User student, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new BadRequestException("Failed to store empty PDF file.");
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.equals("application/pdf")) {
            throw new BadRequestException("Invalid file type. Only PDF files are allowed.");
        }

        // Delete previous resume if present
        resumeRepository.findByStudentId(student.getId()).ifPresent(existing -> resumeRepository.delete(existing));

        Resume resume = new Resume();
        resume.setStudent(student);
        resume.setFileName(file.getOriginalFilename());
        resume.setFileType("application/pdf");
        resume.setFileSize(String.format("%.2f MB", file.getSize() / (1024.0 * 1024.0)));
        resume.setStatus("Uploaded");
        resume.setFileData(file.getBytes());

        return resumeRepository.save(resume);
    }

    public Resume getStudentResume(User student) {
        return resumeRepository.findByStudentId(student.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No resume found for current student profile."));
    }

    @Transactional
    public void deleteStudentResume(User student) {
        Resume resume = getStudentResume(student);
        resumeRepository.delete(resume);
    }
}
