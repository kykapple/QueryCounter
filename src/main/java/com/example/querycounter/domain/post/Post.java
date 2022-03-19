package com.example.querycounter.domain.post;

import com.example.querycounter.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User writer;

    public Post() {
    }

    @Builder
    public Post(User writer, String contents) {
        this.writer = writer;
        this.contents = contents;
    }

}
