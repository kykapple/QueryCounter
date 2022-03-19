package com.example.querycounter.domain.post.repository;

import com.example.querycounter.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
