package com.example.foine.dto;

import java.time.LocalDateTime;

import com.example.foine.entity.Comment;

public class CommentResponseDTO {
    
    private Long id;
    private String comment;
    private String username;
    private LocalDateTime createdAt;
    
    public CommentResponseDTO(Comment c) {
        this.id = c.getId();
        this.comment = c.getComment();
        this.username = c.getUser().getUsername();
        this.createdAt = c.getCreatedAt();
    }

    public Long getId() { return id; }
    public String getComment() { return comment; }
    public String getUsername() { return username; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
