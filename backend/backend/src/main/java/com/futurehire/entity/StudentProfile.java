package com.futurehire.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_profiles")
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private String collegeName;
    private String department;
    private String yearOfStudy;
    private String phone;
    private String cgpa = "8.50";
    private int readinessScore = 85;

    public StudentProfile() {}

    public StudentProfile(User user, String collegeName, String department, String yearOfStudy, String phone) {
        this.user = user;
        this.collegeName = collegeName;
        this.department = department;
        this.yearOfStudy = yearOfStudy;
        this.phone = phone;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getCollegeName() { return collegeName; }
    public void setCollegeName(String collegeName) { this.collegeName = collegeName; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getYearOfStudy() { return yearOfStudy; }
    public void setYearOfStudy(String yearOfStudy) { this.yearOfStudy = yearOfStudy; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getCgpa() { return cgpa; }
    public void setCgpa(String cgpa) { this.cgpa = cgpa; }

    public int getReadinessScore() { return readinessScore; }
    public void setReadinessScore(int readinessScore) { this.readinessScore = readinessScore; }
}
