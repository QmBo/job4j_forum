package ru.job4j.forum.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * RegControl
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 29.06.2020
 */
@Controller
public class RegControl {
    private UserService service;


    /**
     * Instantiates a new Reg control.
     *
     * @param service the service
     */
    @Autowired
    public RegControl(UserService service) {
        this.service = service;

    }

    /**
     * Reg string.
     *
     * @param error the error
     * @param model the model
     * @return the string
     */
    @GetMapping("/reg")
    public String reg(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Create User error.");
        }
        return "reg";
    }

    /**
     * Create user string.
     *
     * @param username the username
     * @param password the password
     * @param req      the req
     * @return the string
     */
    @PostMapping("/reg")
    public String createUser(@RequestParam String username, @RequestParam String password, HttpServletRequest req) {
        String result = "redirect:/reg?error=true";
        User user = new User().setName(username).setPassword(password);
        boolean add = this.service.addUser(user);
        if (add) {
            req.getSession().setAttribute("login", username);
            result = "redirect:/index";
        }
        return result;
    }
}
