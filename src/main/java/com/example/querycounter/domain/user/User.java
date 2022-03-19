package com.example.querycounter.domain.user;

import com.example.querycounter.domain.post.Post;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

}
