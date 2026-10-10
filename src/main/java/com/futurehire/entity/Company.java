package com.futurehire.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String logoUrl;
    private String tier;
    private String ctcRange;
    private String location;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String minCgpa = "7.0 / 10.0";
    private String tenthTwelvethPct = "70% throughout";

    @Column(columnDefinition = "TEXT")
    private String allowedBranches = "CSE, IT, ECE";

    private int maxBacklogs = 0;

    @Column(columnDefinition = "TEXT")
    private String requiredSkills;

    @Column(columnDefinition = "TEXT")
    private String selectionRoundsJson;

    @Column(columnDefinition = "TEXT")
    private String interviewQuestionsJson;

    public Company() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }

    public String getTier() { return tier; }
    public void setTier(String tier) { this.tier = tier; }

    public String getCtcRange() { return ctcRange; }
    public void setCtcRange(String ctcRange) { this.ctcRange = ctcRange; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getMinCgpa() { return minCgpa; }
    public void setMinCgpa(String minCgpa) { this.minCgpa = minCgpa; }

    public String getTenthTwelvethPct() { return tenthTwelvethPct; }
    public void setTenthTwelvethPct(String tenthTwelvethPct) { this.tenthTwelvethPct = tenthTwelvethPct; }

    public String getAllowedBranches() { return allowedBranches; }
    public void setAllowedBranches(String allowedBranches) { this.allowedBranches = allowedBranches; }

    public int getMaxBacklogs() { return maxBacklogs; }
    public void setMaxBacklogs(int maxBacklogs) { this.maxBacklogs = maxBacklogs; }

    public String getRequiredSkills() { return requiredSkills; }
    public void setRequiredSkills(String requiredSkills) { this.requiredSkills = requiredSkills; }

    public String getSelectionRoundsJson() { return selectionRoundsJson; }
    public void setSelectionRoundsJson(String selectionRoundsJson) { this.selectionRoundsJson = selectionRoundsJson; }

    public String getInterviewQuestionsJson() { return interviewQuestionsJson; }
    public void setInterviewQuestionsJson(String interviewQuestionsJson) { this.interviewQuestionsJson = interviewQuestionsJson; }
}
