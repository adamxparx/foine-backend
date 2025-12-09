package com.example.foine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foine.dto.CommentCreateDTO;
import com.example.foine.dto.CommentResponseDTO;
import com.example.foine.security.UserPrincipal;
import com.example.foine.service.CommentService;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin
public class CommentController {
    
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CommentCreateDTO dto, @AuthenticationPrincipal UserPrincipal userPrincipal)     {
        Long userId = userPrincipal.getId();
        return ResponseEntity.ok(commentService.createComment(dto, userId));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByPost(@PathVariable Long postId) {

        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }   
}
