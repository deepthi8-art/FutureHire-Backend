package com.futurehire.controller;

import com.futurehire.dto.response.ApiResponse;
import com.futurehire.entity.MockInterviewBooking;
import com.futurehire.entity.MockInterviewSlot;
import com.futurehire.entity.User;
import com.futurehire.service.AuthService;
import com.futurehire.service.MockInterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/mock-interviews")
public class StudentMockInterviewController {

    @Autowired
    private MockInterviewService mockInterviewService;

    @Autowired
    private AuthService authService;

    @GetMapping("/slots")
    public ResponseEntity<ApiResponse<List<MockInterviewSlot>>> getAvailableSlots() {
        List<MockInterviewSlot> slots = mockInterviewService.getAvailableSlots();
        return ResponseEntity.ok(ApiResponse.success("Available slots fetched", slots));
    }

    @PostMapping("/book/{slotId}")
    public ResponseEntity<ApiResponse<MockInterviewBooking>> bookSlot(@PathVariable Long slotId) {
        User student = authService.getCurrentAuthenticatedUser();
        MockInterviewBooking booking = mockInterviewService.bookSlot(student, slotId);
        return ResponseEntity.ok(ApiResponse.success("Mock interview slot booked successfully", booking));
    }

    @GetMapping("/my-bookings")
    public ResponseEntity<ApiResponse<List<MockInterviewBooking>>> getMyBookings() {
        User student = authService.getCurrentAuthenticatedUser();
        List<MockInterviewBooking> bookings = mockInterviewService.getStudentBookings(student);
        return ResponseEntity.ok(ApiResponse.success("Booked interviews fetched", bookings));
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<ApiResponse<String>> cancelBooking(@PathVariable Long id) {
        User student = authService.getCurrentAuthenticatedUser();
        mockInterviewService.cancelBooking(student, id);
        return ResponseEntity.ok(ApiResponse.success("Booking cancelled", "Cancelled"));
    }
}
