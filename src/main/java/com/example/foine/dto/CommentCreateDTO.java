package com.example.foine.dto;

public class CommentCreateDTO {
    
    private Long imagePostId;
    private String comment;

    public Long getImagePostId() { return imagePostId; }
    public void setImagePostId(Long imagePostId) { this.imagePostId = imagePostId; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}
