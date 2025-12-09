package com.example.foine.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    @JsonBackReference
    private ImagePost imagePost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

    public void setComment(String comment) { this.comment = comment; }
    public String getComment() { return comment; }

    public void setImagePost(ImagePost imagePost) { this.imagePost = imagePost; }
    public ImagePost getImagePost() { return imagePost; }

    public void setUser(User user) { this.user = user; }
    public User getUser() { return user; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
