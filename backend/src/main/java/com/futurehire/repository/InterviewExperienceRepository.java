package com.futurehire.repository;

import com.futurehire.entity.InterviewExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewExperienceRepository extends JpaRepository<InterviewExperience, Long> {
    List<InterviewExperience> findByCompanyNameContainingIgnoreCase(String companyName);
}
