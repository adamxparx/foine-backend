package com.example.foine.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class ImagePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caption;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "imagePost", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Comment> comments;
    
    @OneToMany(mappedBy = "imagePost", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Likes> likes;
    
    @OneToMany(mappedBy = "imagePost", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Saves> saves;
    
    public ImagePost() {}

    public ImagePost(String caption) {
        this.caption = caption;
    }
    
    public ImagePost(String caption, String imageUrl, User user) {
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    public List<Comment> getComments() { return comments; }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
}

