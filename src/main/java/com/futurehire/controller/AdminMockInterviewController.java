package com.futurehire.controller;

import com.futurehire.dto.response.ApiResponse;
import com.futurehire.entity.MockInterviewBooking;
import com.futurehire.entity.MockInterviewSlot;
import com.futurehire.service.MockInterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/mock-interviews")
@PreAuthorize("hasRole('ADMIN')")
public class AdminMockInterviewController {

    @Autowired
    private MockInterviewService mockInterviewService;

    @PostMapping("/slots")
    public ResponseEntity<ApiResponse<MockInterviewSlot>> createSlot(@RequestBody MockInterviewSlot slot) {
        MockInterviewSlot created = mockInterviewService.scheduleNewSlot(slot);
        return ResponseEntity.ok(ApiResponse.success("Mock interview slot scheduled", created));
    }

    @GetMapping("/slots")
    public ResponseEntity<ApiResponse<List<MockInterviewSlot>>> getAllSlots() {
        List<MockInterviewSlot> slots = mockInterviewService.getAllSlots();
        return ResponseEntity.ok(ApiResponse.success("All mock slots fetched", slots));
    }

    @GetMapping("/bookings")
    public ResponseEntity<ApiResponse<List<MockInterviewBooking>>> getAllBookings() {
        List<MockInterviewBooking> bookings = mockInterviewService.getAllBookings();
        return ResponseEntity.ok(ApiResponse.success("All interview bookings fetched", bookings));
    }

    @PutMapping("/bookings/{id}/feedback")
    public ResponseEntity<ApiResponse<MockInterviewBooking>> submitFeedback(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        Double rating = Double.parseDouble(request.get("rating").toString());
        String strengths = request.get("strengths").toString();
        String areasToImprove = request.get("areasToImprove").toString();

        MockInterviewBooking updated = mockInterviewService.submitFeedback(id, rating, strengths, areasToImprove);
        return ResponseEntity.ok(ApiResponse.success("Student interview feedback submitted", updated));
    }
}
