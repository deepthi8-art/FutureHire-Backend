package com.futurehire.repository;

import com.futurehire.entity.AptitudeQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AptitudeQuizRepository extends JpaRepository<AptitudeQuiz, Long> {
    List<AptitudeQuiz> findByCategoryContainingIgnoreCase(String category);
}
