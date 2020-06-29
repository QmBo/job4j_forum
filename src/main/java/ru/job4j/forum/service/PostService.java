package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PostService
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 26.06.2020
 */
@Service
public class PostService {

    private final List<Post> posts = new ArrayList<>();
    private int lastId;
    private final UserService userService;

    /**
     * Instantiates a new Post service.
     *
     * @param userService the user service
     */
    public PostService(UserService userService) {
        this.userService = userService;
        posts.add(new Post()
                .setName("Продаю машину ладу 01.")
                .setId(1).setDesc("на ходу")
                .setTopic(true)
                .setAuthor(userService.getUserByName("admin"))
        );
        this.lastId = posts.size();
    }

    /**
     * Gets all topics.
     *
     * @return the all topics
     */
    public List<Post> getAllTopics() {
        return posts.stream().filter(Post::isTopic).collect(Collectors.toList());
    }

    /**
     * Add or update topic post.
     *
     * @param req the req
     * @return the post
     */
    public Post addOrUpdateTopic(HttpServletRequest req) {
        Post result = new Post();
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String author = req.getParameter("author");
        if (name != null && description != null && author != null) {
            User user = this.userService.getUserByName(author);
            result.setAuthor(user).setDesc(description).setName(name);
            result = this.addOrUpdate(result, req);
        }
        return result;
    }

    private Post addOrUpdate(Post post, HttpServletRequest req) {
        Post result = post;
        String oldPostId = req.getParameter("oldPostId");
        String answerFor = req.getParameter("answerFor");
        if (oldPostId == null && answerFor == null) {
            post.setTopic(true);
            result = addPost(post);
        } else {
            if (oldPostId != null) {
                Post byId = this.getById(new Post().setId(Integer.parseInt(oldPostId)));
                if (byId != null) {
                    result = byId.setName(post.getName()).setDesc(post.getDesc());
                    Post topicPost = result.getTopicPost();
                    if (topicPost != null) {
                        result = topicPost;
                    }
                }
            }
            if (answerFor != null) {
                int id = Integer.parseInt(answerFor);
                Post answerForPost = this.getById(new Post().setId(id));
                if (answerForPost != null) {
                    answerForPost.addAnswer(post);
                    this.addPost(post);
                    result = answerForPost;
                }
            }
        }
        return result;
    }

    private Post addPost(Post post) {
        this.posts.add(post);
        return post.setId(++this.lastId);
    }


    /**
     * Gets by id.
     *
     * @param post the post
     * @return the by id
     */
    public Post getById(final Post post) {
        return this.posts.stream().filter(p -> post.getId() == p.getId()).findFirst().orElse(null);
    }
}
