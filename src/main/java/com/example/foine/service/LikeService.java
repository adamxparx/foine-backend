package com.example.foine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foine.dto.LikeResponseDTO;
import com.example.foine.entity.ImagePost;
import com.example.foine.entity.Like;
import com.example.foine.entity.User;
import com.example.foine.repository.ImagePostRepository;
import com.example.foine.repository.LikeRepository;
import com.example.foine.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class LikeService {
    
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private ImagePostRepository imagePostRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public LikeResponseDTO toggleLike(Long userId, Long postId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found."));

        ImagePost post = imagePostRepository.findById(postId)
            .orElseThrow(() -> new RuntimeException("Post not found."));

        return likeRepository.findByUserIdAndImagePostId(userId, postId)
            .map(existingLike -> {

                likeRepository.delete(existingLike);

                long newCount = likeRepository.countByImagePostId(postId);

                return new LikeResponseDTO(postId, newCount, false);
            })
            .orElseGet(() -> {

                Like like = new Like();
                like.setUser(user);
                like.setImagePost(post);

                likeRepository.save(like);
                
                long newCount = likeRepository.countByImagePostId(postId);

                return new LikeResponseDTO(postId, newCount, true);
            });
    }

    public LikeResponseDTO getLikeStatus(Long userId, Long postId) {

        boolean liked = likeRepository
            .findByUserIdAndImagePostId(userId, postId)
            .isPresent();

        long count = likeRepository.countByImagePostId(postId);

        return new LikeResponseDTO(postId, count, liked);
    }
}
