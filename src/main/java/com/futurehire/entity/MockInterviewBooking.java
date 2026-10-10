package com.futurehire.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mock_interview_bookings")
public class MockInterviewBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "slot_id", nullable = false, unique = true)
    private MockInterviewSlot slot;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    private LocalDateTime bookedAt = LocalDateTime.now();

    private Double feedbackRating;

    @Column(columnDefinition = "TEXT")
    private String feedbackStrengths;

    @Column(columnDefinition = "TEXT")
    private String feedbackAreasToImprove;

    public MockInterviewBooking() {}

    public MockInterviewBooking(MockInterviewSlot slot, User student) {
        this.slot = slot;
        this.student = student;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public MockInterviewSlot getSlot() { return slot; }
    public void setSlot(MockInterviewSlot slot) { this.slot = slot; }

    public User getStudent() { return student; }
    public void setStudent(User student) { this.student = student; }

    public LocalDateTime getBookedAt() { return bookedAt; }
    public void setBookedAt(LocalDateTime bookedAt) { this.bookedAt = bookedAt; }

    public Double getFeedbackRating() { return feedbackRating; }
    public void setFeedbackRating(Double feedbackRating) { this.feedbackRating = feedbackRating; }

    public String getFeedbackStrengths() { return feedbackStrengths; }
    public void setFeedbackStrengths(String feedbackStrengths) { this.feedbackStrengths = feedbackStrengths; }

    public String getFeedbackAreasToImprove() { return feedbackAreasToImprove; }
    public void setFeedbackAreasToImprove(String feedbackAreasToImprove) { this.feedbackAreasToImprove = feedbackAreasToImprove; }
}
