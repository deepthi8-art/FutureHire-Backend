package com.futurehire.repository;

import com.futurehire.entity.MockInterviewSlot;
import com.futurehire.enums.SlotStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MockInterviewSlotRepository extends JpaRepository<MockInterviewSlot, Long> {
    List<MockInterviewSlot> findByStatus(SlotStatus status);
}
