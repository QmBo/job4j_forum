package ru.job4j.forum.model;

/**
 * User
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 26.06.2020
 */
public class User {
    private String name;
    private String password;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public User setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     * @return the password
     */
    public User setPassword(String password) {
        this.password = password;
        return this;
    }
}
