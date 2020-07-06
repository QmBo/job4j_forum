package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.store.UserRepository;


/**
 * UserService
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 26.06.2020
 */
@Service
public class UserService {
    private final UserRepository userList;

    /**
     * Instantiates a new User service.
     *
     * @param userList the user list
     */
    public UserService(UserRepository userList) {
        this.userList = userList;
    }

    /**
     * Add user.
     *
     * @param user the user
     */
    public void addUser(final User user) {
        this.userList.save(user);
    }

    /**
     * Gets user by name.
     *
     * @param author the author
     * @return the user by name
     */
    public User getUserByName(final String author) {
        return this.userList.findByName(author).orElse(null);
    }

    /**
     * Login available boolean.
     *
     * @param login the login
     * @return the boolean
     */
    public boolean loginAvailable(final String login) {
        return !this.userList.findByName(login).isPresent();
    }

    /**
     * Is credentials boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean isCredentials(final User user) {
        return this.userList.findByNameAndPassword(user.getName(), user.getPassword()).isPresent();
    }
}
