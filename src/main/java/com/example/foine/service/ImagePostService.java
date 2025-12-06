package com.example.foine.service;

import com.example.foine.cloudinary.CloudinaryService;
import com.example.foine.entity.ImagePost;
import com.example.foine.entity.User;
import com.example.foine.repository.ImagePostRepository;
import com.example.foine.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagePostService {

    @Autowired
    private ImagePostRepository imagePostRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private UserRepository userRepository;

    public ImagePost createImagePost(MultipartFile image, String caption, String userEmail) {

        String imageUrl = cloudinaryService.uploadImage(image);

        User user = userRepository.findByEmail(userEmail);
        
        if (user == null) {
            throw new RuntimeException("User not found.");
        }

        ImagePost imagePost = new ImagePost();
        imagePost.setImageUrl(imageUrl);
        imagePost.setCaption(caption);
        imagePost.setUser(user);
        imagePost.setCreatedAt(LocalDateTime.now());

        return imagePostRepository.save(imagePost);
    }

    public List<ImagePost> getAllImagePosts() {
        return imagePostRepository.findAllByOrderByCreatedAtDesc();
    }

    public ImagePost getImagePostById(Long id) {
        return imagePostRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Image post not found."));
    }
}
