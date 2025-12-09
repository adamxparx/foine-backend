package com.example.foine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foine.dto.LikeResponseDTO;
import com.example.foine.security.UserPrincipal;
import com.example.foine.service.LikeService;

@RestController
@RequestMapping("/api/likes")
@CrossOrigin(origins = "*")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/{postId}/toggle")
    public ResponseEntity<LikeResponseDTO> toggleLike(@PathVariable Long postId, @AuthenticationPrincipal UserPrincipal userPrincipal) {

        Long userId = userPrincipal.getId();

        LikeResponseDTO response = likeService.toggleLike(userId, postId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{postId}/status")
    public ResponseEntity<LikeResponseDTO> getLikeStatus(@PathVariable Long postId, @AuthenticationPrincipal UserPrincipal userPrincipal) {

        Long userId = userPrincipal.getId();

        LikeResponseDTO response = likeService.getLikeStatus(userId, postId);

        return ResponseEntity.ok(response);
    }
    
}
