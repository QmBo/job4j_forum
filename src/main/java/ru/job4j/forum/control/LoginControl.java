package ru.job4j.forum.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

/**
 * LoginControl
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 26.06.2020
 */
@Controller
public class LoginControl {
    private UserService userService;

    /**
     * Instantiates a new Login control.
     *
     * @param userService the user service
     */
    @Autowired
    public LoginControl(UserService userService) {
        this.userService = userService;
    }

    /**
     * Login page string.
     *
     * @param error  the error
     * @param logout the logout
     * @param model  the model
     * @return the string
     * @noinspection ConstantConditions
     */
    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String error,
                            @RequestParam(required = false) String logout,
                            Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Имя пользователя или пароль введены не правильно!!";
        }
        if (logout != null) {
            errorMessage = "Вы удачно вышли из системы!!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    /**
     * Login check string.
     *
     * @param username the username
     * @param password the password
     * @param req      the req
     * @return the string
     */
    @PostMapping("/login")
    public String loginCheck(@RequestParam String username, @RequestParam String password, HttpServletRequest req) {
        String result = "redirect:/login?error=true";
        if (this.userService.isCredentials(new User().setName(username).setPassword(password))) {
            req.getSession().setAttribute("login", username);
            result = "redirect:/index";
        }
        return result;
    }

    /**
     * Logout page string.
     *
     * @param request the request
     * @return the string
     */
    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login?logout=true";
    }
}
