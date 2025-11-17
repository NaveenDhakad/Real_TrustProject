package com.flipr.placement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    @Column(name = "image_url")
    private String imageUrl;  // Store image path like "/images/project1.jpg" or full URL

    @Setter
    @Getter
    @Column(name = "image_data", columnDefinition = "LONGBLOB")
    private byte[] imageData;  // If storing actual image bytes in DB
    @Column(name = "client_name", nullable = false)
    private String name;

    @Column(name = "client_description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "client_designation")
    private String designation;



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
