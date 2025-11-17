package com.flipr.placement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Setter
    @Column(name = "image_url")
    private String imageUrl;  // Store image path like "/images/project1.jpg" or full URL

    @Getter
    @Setter
    @Column(name = "image_data", columnDefinition = "LONGBLOB")
    private byte[] imageData;  // If storing actual image bytes in DB
    @Column(name = "project_name", nullable = false)
    private String name;

    @Column(name = "project_description", columnDefinition = "TEXT")
    private String description;


    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
