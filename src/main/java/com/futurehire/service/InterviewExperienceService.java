package com.futurehire.service;

import com.futurehire.entity.InterviewExperience;
import com.futurehire.entity.User;
import com.futurehire.exception.BadRequestException;
import com.futurehire.exception.ResourceNotFoundException;
import com.futurehire.repository.InterviewExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewExperienceService {

    @Autowired
    private InterviewExperienceRepository experienceRepository;

    public List<InterviewExperience> getAllExperiences() {
        return experienceRepository.findAll();
    }

    public InterviewExperience getExperienceById(Long id) {
        return experienceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interview experience not found with id: " + id));
    }

    public List<InterviewExperience> searchByCompany(String company) {
        if (company == null || company.trim().isEmpty() || company.equalsIgnoreCase("All")) {
            return getAllExperiences();
        }
        return experienceRepository.findByCompanyNameContainingIgnoreCase(company);
    }

    public InterviewExperience shareExperience(User student, InterviewExperience experience) {
        experience.setStudent(student);
        return experienceRepository.save(experience);
    }

    public InterviewExperience updateExperience(User student, Long id, InterviewExperience updatedDetails) {
        InterviewExperience exp = getExperienceById(id);
        if (!exp.getStudent().getId().equals(student.getId())) {
            throw new BadRequestException("You are not authorized to edit this interview experience.");
        }
        exp.setCompanyName(updatedDetails.getCompanyName());
        exp.setRoleTitle(updatedDetails.getRoleTitle());
        exp.setVerdict(updatedDetails.getVerdict());
        exp.setPackageOffered(updatedDetails.getPackageOffered());
        exp.setContent(updatedDetails.getContent());
        exp.setTips(updatedDetails.getTips());
        return experienceRepository.save(exp);
    }

    public void deleteExperience(User student, Long id) {
        InterviewExperience exp = getExperienceById(id);
        if (!exp.getStudent().getId().equals(student.getId()) && !student.getRole().getName().name().equals("ROLE_ADMIN")) {
            throw new BadRequestException("You are not authorized to delete this experience.");
        }
        experienceRepository.delete(exp);
    }
}
