package com.example.querycounter;

import com.example.querycounter.counter.Counter;
import com.example.querycounter.domain.post.Post;
import com.example.querycounter.domain.post.repository.PostRepository;
import com.example.querycounter.domain.user.User;
import com.example.querycounter.domain.user.repository.UserRepository;
import com.example.querycounter.domain.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QueryCounterTest {

    @Autowired
    private Counter counter;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        for (int i = 1; i <= 10; i++) {
            User user = userRepository.save(new User(i + "번 user"));

            Post post = Post.builder()
                    .writer(user)
                    .contents(i + "번 post")
                    .build();

            postRepository.save(post);
        }
    }

    @Test
    void N_Plus_1_Problem() {
        counter.startQueryCount();

        List<String> userPostContentsList = userService.getAllPostContents();

        assertThat(userPostContentsList).hasSize(10);
        assertThat(counter.getQueryCount()).isEqualTo(11);
        counter.printQueryCount();
    }

}
