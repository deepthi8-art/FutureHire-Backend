package com.futurehire.repository;

import com.futurehire.entity.MockInterviewBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MockInterviewBookingRepository extends JpaRepository<MockInterviewBooking, Long> {
    List<MockInterviewBooking> findByStudentId(Long studentId);
    Optional<MockInterviewBooking> findBySlotId(Long slotId);
    boolean existsBySlotId(Long slotId);
}
