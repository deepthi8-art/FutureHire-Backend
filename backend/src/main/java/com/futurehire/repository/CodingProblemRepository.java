package com.futurehire.repository;

import com.futurehire.entity.CodingProblem;
import com.futurehire.enums.DifficultyLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodingProblemRepository extends JpaRepository<CodingProblem, Long> {
    List<CodingProblem> findByDifficulty(DifficultyLevel difficulty);
    List<CodingProblem> findByCategoryContainingIgnoreCase(String category);
}
