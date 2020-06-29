package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * UserService
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 26.06.2020
 */
@Service
public class UserService {
    private final List<User> userList = new ArrayList<>(100);

    /**
     * Instantiates a new User service.
     */
    public UserService() {
        this.userList.add(new User().setName("user").setPassword("123456"));
        this.userList.add(new User().setName("admin").setPassword("123456"));
    }

    /**
     * Add user.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean addUser(final User user) {
        return this.userList.add(user);
    }

    /**
     * Gets user by name.
     *
     * @param author the author
     * @return the user by name
     */
    User getUserByName(String author) {
        return this.userList.stream().filter(user -> author.equals(user.getName())).findFirst().orElse(null);
    }

    /**
     * Login available boolean.
     *
     * @param login the login
     * @return the boolean
     */
    public boolean loginAvailable(final String login) {
        return this.userList.stream().noneMatch(user -> user.getName().equals(login));
    }

    /**
     * Is credentials boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean isCredentials(User user) {
        boolean result = false;
        User userByName = this.getUserByName(user.getName());
        if (userByName != null) {
            if (userByName.getPassword().equals(user.getPassword())) {
                result = true;
            }
        }
        return result;
    }
}
