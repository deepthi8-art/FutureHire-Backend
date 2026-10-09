package com.futurehire.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aptitude_quizzes")
public class AptitudeQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String category; // Quantitative Aptitude, Logical Reasoning, Verbal Ability
    private int durationMinutes = 15;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AptitudeQuestion> questions = new ArrayList<>();

    public AptitudeQuiz() {}

    public AptitudeQuiz(String title, String category, int durationMinutes) {
        this.title = title;
        this.category = category;
        this.durationMinutes = durationMinutes;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }

    public List<AptitudeQuestion> getQuestions() { return questions; }
    public void setQuestions(List<AptitudeQuestion> questions) { this.questions = questions; }

    public void addQuestion(AptitudeQuestion q) {
        questions.add(q);
        q.setQuiz(this);
    }
}
