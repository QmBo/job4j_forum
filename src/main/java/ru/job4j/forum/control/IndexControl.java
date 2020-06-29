package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.service.PostService;

/**
 * IndexControl
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 26.06.2020
 */
@Controller
public class IndexControl {
    private final PostService posts;

    /**
     * Instantiates a new Index control.
     *
     * @param posts the posts
     */
    public IndexControl(PostService posts) {
        this.posts = posts;
    }

    /**
     * Index string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("posts", posts.getAllTopics());
        return "index";
    }
}
