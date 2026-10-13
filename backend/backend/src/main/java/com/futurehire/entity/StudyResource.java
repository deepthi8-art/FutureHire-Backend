package com.futurehire.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "study_resources")
public class StudyResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String category;
    private String format = "PDF";
    private String fileSize = "10.5 MB";
    private long downloads = 1200;
    private String author = "FutureHire Mentors";

    private String fileUrl = "#";

    private LocalDateTime uploadedAt = LocalDateTime.now();

    public StudyResource() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }

    public String getFileSize() { return fileSize; }
    public void setFileSize(String fileSize) { this.fileSize = fileSize; }

    public long getDownloads() { return downloads; }
    public void setDownloads(long downloads) { this.downloads = downloads; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }
}
