# QueryCounter
- JPA Query counter with dynamic proxy
- Implemented to count the number of queries occurring in JPA

<br>

## Usage
**1. Specify the path to the entity in the entity manager.**
```java
/config/QueryCounterConfig.java

@Configuration
public class QueryCountConfig {

    ...

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(queryCountDataSource());
        em.setPackagesToScan(
            // Specify the path to the entity
            "com.example.querycounter.domain",
            ...
        );

        ...

        return em;
    }
    
    ...
}
```

**2. Inject a Counter bean and start query count**
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QueryCounterTest {

    @Autowired
    private Counter counter;

    @Test
    void testQueryCount() {
        counter.startQueryCount();

        // Business logic that wants to count the number of queries

        assertThat(counter.getQueryCount()).isEqualTo("Expected number of queries");
        counter.printQueryCount();
    }

}
```

<br>

## Example
```java
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
```
![9](https://user-images.githubusercontent.com/76088639/162624482-fe4b5488-edc3-42a7-bcd2-77f173b42d8c.png)


