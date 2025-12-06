package com.example.foine.dto;

import java.time.LocalDateTime;

import com.example.foine.entity.ImagePost;

public class ImagePostDTO {
    private Long id;
    private String imageUrl;
    private String caption;
    private String username;
    private LocalDateTime createdAt;

    public ImagePostDTO(ImagePost imagePost) {
        this.id = imagePost.getId();
        this.imageUrl = imagePost.getImageUrl();
        this.caption = imagePost.getCaption();
        this.username = imagePost.getUser().getUsername();
        this.createdAt = imagePost.getCreatedAt();
    }

    public String getCaption() { return caption; }
    public void setCaption(String caption) { this.caption = caption; }
    public Long getUserId() { return id; }
    public void setUserId(Long id) { this.id = id; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
