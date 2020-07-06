package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

/**
 * EditControl
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 26.06.2020
 */
@Controller
public class EditControl {
    private PostService postService;
    private UserService userService;

    /**
     * Instantiates a new Edit control.
     *
     * @param service     the postService
     * @param userService the user service
     */
    public EditControl(PostService service, UserService userService) {
        this.postService = service;
        this.userService = userService;
    }

    /**
     * Index string.
     *
     * @param error     the error
     * @param answerFor the answer for
     * @param id        the id
     * @param model     the model
     * @return the string
     */
    @GetMapping({"/create"})
    public String index(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String answerFor,
            @RequestParam(required = false) String id,
            Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Topic create error.");
        }
        if (id != null) {
            model.addAttribute("oldPost", this.postService.getById(new Post().setId(Long.valueOf(id))));
        }
        if (answerFor != null) {
            model.addAttribute("answerFor", answerFor);
        }
        model.addAttribute("action", "/create");
        return "edit";
    }

    /**
     * Create topic string.
     *
     * @param name        the name
     * @param description the description
     * @param author      the author
     * @param oldPostId   the old post id
     * @param answerFor   the answer for
     * @return the string
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createTopic(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String oldPostId,
            @RequestParam(required = false) String answerFor) {
        String result = "redirect:/create?error=true";
        if (name != null && description != null && author != null) {
            User user = this.userService.getUserByName(author);
            Post post = new Post().setAuthor(user).setDescription(description).setName(name);
            if (oldPostId != null) {
                this.postService.updatePost(post.setId(Long.valueOf(oldPostId)));
            } else {
                if (answerFor != null) {
                    post.setTopicPost(this.postService.getById(new Post().setId(Long.valueOf(answerFor))));
                } else {
                    post.setTopic(true);
                }
                this.postService.addPost(post);
            }
            if (post.getId() != 0L) {
                post = this.postService.getTopic(post);
                result = String.format("redirect:/post?id=%s", post.getId());
            }
        }
        return result;
    }
}
