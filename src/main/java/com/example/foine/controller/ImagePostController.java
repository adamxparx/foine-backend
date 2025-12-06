package com.example.foine.controller;

import com.example.foine.dto.ImagePostDTO;
import com.example.foine.entity.ImagePost;
import java.util.List;
import com.example.foine.service.ImagePostService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image-posts")
public class ImagePostController {

    @Autowired
    private ImagePostService imagePostService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> createImagePost(
        @RequestParam("image") MultipartFile image,
        @RequestParam("caption") String caption,
        Authentication authentication
    ) {
        String userEmail = authentication.getName();

        ImagePost imagePost = imagePostService.createImagePost(image, caption, userEmail);

        return ResponseEntity.ok(imagePost);
    }

    @GetMapping
    public ResponseEntity<List<ImagePostDTO>> getAllImagePosts() {
        List<ImagePost> posts = imagePostService.getAllImagePosts();
        List<ImagePostDTO> dtos = posts.stream().map(ImagePostDTO::new).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ImagePost> getImagePostById(@PathVariable Long id) {
        ImagePost post = imagePostService.getImagePostById(id);
        return ResponseEntity.ok(post);
    }
}
