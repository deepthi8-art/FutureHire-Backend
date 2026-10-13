package com.futurehire.repository;

import com.futurehire.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Optional<Resume> findByStudentId(Long studentId);
    void deleteByStudentId(Long studentId);
}
