package com.example.querycounter.domain.user.repository;

import com.example.querycounter.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
