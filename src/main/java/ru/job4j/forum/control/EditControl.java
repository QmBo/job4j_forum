package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import javax.servlet.http.HttpServletRequest;

/**
 * EditControl
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 26.06.2020
 */
@Controller
public class EditControl {
    private PostService service;

    /**
     * Instantiates a new Edit control.
     *
     * @param service the service
     */
    public EditControl(PostService service) {
        this.service = service;
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
            model.addAttribute("oldPost", this.service.getById(new Post().setId(Integer.valueOf(id))));
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
     * @param req the req
     * @return the string
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createTopic(HttpServletRequest req) {
        String result = "redirect:/create?error=true";
        Post post = this.service.addOrUpdateTopic(req);
        int id = post.getId();
        if (id != 0) {
            result = String.format("redirect:/post?id=%s", id);
        }
        return result;
    }
}
