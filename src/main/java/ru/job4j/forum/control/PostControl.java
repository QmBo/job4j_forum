package ru.job4j.forum.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

/**
 * PostControl
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 26.06.2020
 */
@Controller
public class PostControl {
    private final PostService postService;

    /**
     * Instantiates a new Post control.
     *
     * @param postService the post service
     */
    @Autowired
    public PostControl(PostService postService) {
        this.postService = postService;
    }

    /**
     * Show post string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping({"/post"})
    public String showPost(@RequestParam(required = false)String id, Model model) {
        String result = "redirect:/";
        if (id != null) {
            Post post = this.postService.getById(new Post().setId(Long.valueOf(id)));
            if (post != null) {
                model.addAttribute("post", post);
                result = "post";
            }
        }
        return result;
    }
}
