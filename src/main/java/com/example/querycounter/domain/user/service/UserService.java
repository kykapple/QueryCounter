package com.example.querycounter.domain.user.service;

import com.example.querycounter.domain.post.Post;
import com.example.querycounter.domain.user.User;
import com.example.querycounter.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> getAllPostContents(Long userId) {
        return userRepository.findAll()
                .stream()
                .map(User::getPosts)
                .flatMap(Collection::stream)
                .map(Post::getContents)
                .collect(Collectors.toList());
    }

}
