package com.futurehire.service;

import com.futurehire.entity.MockInterviewBooking;
import com.futurehire.entity.MockInterviewSlot;
import com.futurehire.entity.User;
import com.futurehire.enums.SlotStatus;
import com.futurehire.exception.BadRequestException;
import com.futurehire.exception.ResourceNotFoundException;
import com.futurehire.repository.MockInterviewBookingRepository;
import com.futurehire.repository.MockInterviewSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MockInterviewService {

    @Autowired
    private MockInterviewSlotRepository slotRepository;

    @Autowired
    private MockInterviewBookingRepository bookingRepository;

    public List<MockInterviewSlot> getAvailableSlots() {
        return slotRepository.findByStatus(SlotStatus.AVAILABLE);
    }

    public List<MockInterviewSlot> getAllSlots() {
        return slotRepository.findAll();
    }

    public MockInterviewSlot getSlotById(Long id) {
        return slotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mock interview slot not found with id: " + id));
    }

    @Transactional
    public MockInterviewBooking bookSlot(User student, Long slotId) {
        MockInterviewSlot slot = getSlotById(slotId);

        if (slot.getStatus() != SlotStatus.AVAILABLE) {
            throw new BadRequestException("This mock interview slot is no longer available for booking.");
        }

        if (bookingRepository.existsBySlotId(slotId)) {
            throw new BadRequestException("This slot has already been booked by another student.");
        }

        slot.setStatus(SlotStatus.BOOKED);
        slot.setMeetingLink("https://meet.google.com/futurehire-" + Long.toHexString(System.currentTimeMillis()));
        slotRepository.save(slot);

        MockInterviewBooking booking = new MockInterviewBooking(slot, student);
        return bookingRepository.save(booking);
    }

    public List<MockInterviewBooking> getStudentBookings(User student) {
        return bookingRepository.findByStudentId(student.getId());
    }

    public List<MockInterviewBooking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Transactional
    public void cancelBooking(User student, Long bookingId) {
        MockInterviewBooking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));

        if (!booking.getStudent().getId().equals(student.getId()) && !student.getRole().getName().name().equals("ROLE_ADMIN")) {
            throw new BadRequestException("Not authorized to cancel this booking.");
        }

        MockInterviewSlot slot = booking.getSlot();
        slot.setStatus(SlotStatus.AVAILABLE);
        slot.setMeetingLink(null);
        slotRepository.save(slot);

        bookingRepository.delete(booking);
    }

    public MockInterviewSlot scheduleNewSlot(MockInterviewSlot slot) {
        slot.setStatus(SlotStatus.AVAILABLE);
        return slotRepository.save(slot);
    }

    public MockInterviewBooking submitFeedback(Long bookingId, Double rating, String strengths, String areasToImprove) {
        MockInterviewBooking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));

        booking.setFeedbackRating(rating);
        booking.setFeedbackStrengths(strengths);
        booking.setFeedbackAreasToImprove(areasToImprove);

        MockInterviewSlot slot = booking.getSlot();
        slot.setStatus(SlotStatus.COMPLETED);
        slotRepository.save(slot);

        return bookingRepository.save(booking);
    }
}
