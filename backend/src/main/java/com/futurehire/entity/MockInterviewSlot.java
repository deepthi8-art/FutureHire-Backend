package com.futurehire.entity;

import com.futurehire.enums.SlotStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "mock_interview_slots")
public class MockInterviewSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String mentorName;

    private String mentorTitle;
    private String date;
    private String timeWindow;
    private String interviewType;

    @Enumerated(EnumType.STRING)
    private SlotStatus status = SlotStatus.AVAILABLE;

    private String meetingLink;

    public MockInterviewSlot() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMentorName() { return mentorName; }
    public void setMentorName(String mentorName) { this.mentorName = mentorName; }

    public String getMentorTitle() { return mentorTitle; }
    public void setMentorTitle(String mentorTitle) { this.mentorTitle = mentorTitle; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTimeWindow() { return timeWindow; }
    public void setTimeWindow(String timeWindow) { this.timeWindow = timeWindow; }

    public String getInterviewType() { return interviewType; }
    public void setInterviewType(String interviewType) { this.interviewType = interviewType; }

    public SlotStatus getStatus() { return status; }
    public void setStatus(SlotStatus status) { this.status = status; }

    public String getMeetingLink() { return meetingLink; }
    public void setMeetingLink(String meetingLink) { this.meetingLink = meetingLink; }
}
