package com.example.foine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.foine.entity.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserIdAndImagePostId(Long userId, Long postId);

    long countByImagePostId(Long postId);
}
