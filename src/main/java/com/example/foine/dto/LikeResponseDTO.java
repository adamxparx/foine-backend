package com.example.foine.dto;

public class LikeResponseDTO {
    
    private Long postId;
    private long likeCount;
    private boolean likedByCurrentUser;

    public LikeResponseDTO(Long postId, long likeCount, boolean likedByCurrentUser) {
        this.postId = postId;
        this.likeCount = likeCount;
        this.likedByCurrentUser = likedByCurrentUser;
    }

    public Long getPostId() { return postId; }
    public long getLikeCount() { return likeCount; }
    public boolean getLikedByCurrentUser() { return likedByCurrentUser; }
}
