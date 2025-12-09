package com.example.foine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foine.dto.CommentCreateDTO;
import com.example.foine.dto.CommentResponseDTO;
import com.example.foine.entity.Comment;
import com.example.foine.entity.ImagePost;
import com.example.foine.entity.User;
import com.example.foine.repository.CommentRepository;
import com.example.foine.repository.ImagePostRepository;
import com.example.foine.repository.UserRepository;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ImagePostRepository imagePostRepository;

    @Autowired
    private UserRepository userRepository;

    public CommentResponseDTO createComment(CommentCreateDTO dto, Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found."));

        ImagePost post = imagePostRepository.findById(dto.getImagePostId())
            .orElseThrow(() -> new RuntimeException("Post not found."));

        Comment comment = new Comment();
        comment.setComment(dto.getComment());
        comment.setUser(user);
        comment.setImagePost(post);

        Comment saved = commentRepository.save(comment);

        return new CommentResponseDTO(saved);
    }

    public List<CommentResponseDTO> getCommentsByPost(Long postId) {
        return commentRepository.findByImagePostIdOrderByCreatedAtDesc(postId)
            .stream()
            .map(CommentResponseDTO::new)
            .toList();
    }
}
