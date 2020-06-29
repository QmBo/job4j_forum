package ru.job4j.forum.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.forum.service.UserService;

/**
 * RegRESTControl
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 29.06.2020
 */
@RestController
@RequestMapping("/reg")
public class RegRESTControl {
    private UserService service;

    /**
     * Instantiates a new Reg rest control.
     *
     * @param service the service
     */
    @Autowired
    public RegRESTControl(UserService service) {
        this.service = service;
    }

    /**
     * Available response entity.
     *
     * @param login the login
     * @return the response entity
     */
    @GetMapping("/{login}")
    public ResponseEntity<Boolean> available(@PathVariable("login") String login) {
        return ResponseEntity.ok(this.service.loginAvailable(login));
    }
}
