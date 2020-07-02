package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.store.PostRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * PostService
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 26.06.2020
 */
@Service
public class PostService {

    private final PostRepository posts;

    /**
     * Instantiates a new Post service.
     *
     * @param posts the posts
     */
    public PostService(PostRepository posts) {
        this.posts = posts;
    }

    /**
     * Gets all topics.
     *
     * @return the all topics
     */
    public List<Post> getAllTopics() {
        return this.posts.findByTopic(true);
    }

    /**
     * Add post post.
     *
     * @param post the post
     * @return the post
     */
    public Post addPost(final Post post) {
        return this.posts.save(post);
    }

    /**
     * Gets by id.
     *
     * @param post the post
     * @return the by id
     */
    public Post getById(final Post post) {
        return this.posts.findById(post.getId()).orElse(null);
    }

    /**
     * Update post post.
     *
     * @param post the post
     * @return the post
     */
    public Post updatePost(final Post post) {
        List<Post> result = new ArrayList<>(100);
        this.posts.findById(post.getId())
                .ifPresent(p -> result.add(
                        this.addPost(p.setName(post.getName()).setDescription(post.getDescription()))
                        )
                );
        return result.size() == 1 ? result.get(0) : null;
    }

    /**
     * Gets topic.
     *
     * @param post the post
     * @return the topic
     */
    public Post getTopic(final Post post) {
        Post result = post;
        if (post.getTopicPost() != null) {
            result = post.getTopicPost();
        }
        return result;
    }
}
