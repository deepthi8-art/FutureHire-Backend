package com.futurehire.entity;

import com.futurehire.enums.SubmissionStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "coding_submissions")
public class CodingSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    private CodingProblem problem;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String sourceCode;

    private String language;

    @Enumerated(EnumType.STRING)
    private SubmissionStatus status;

    private String runtime = "38 ms";
    private String memory = "13.8 MB";

    private LocalDateTime submittedAt = LocalDateTime.now();

    public CodingSubmission() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getStudent() { return student; }
    public void setStudent(User student) { this.student = student; }

    public CodingProblem getProblem() { return problem; }
    public void setProblem(CodingProblem problem) { this.problem = problem; }

    public String getSourceCode() { return sourceCode; }
    public void setSourceCode(String sourceCode) { this.sourceCode = sourceCode; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public SubmissionStatus getStatus() { return status; }
    public void setStatus(SubmissionStatus status) { this.status = status; }

    public String getRuntime() { return runtime; }
    public void setRuntime(String runtime) { this.runtime = runtime; }

    public String getMemory() { return memory; }
    public void setMemory(String memory) { this.memory = memory; }

    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }
}
