package ru.job4j.forum.store;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.User;

import java.util.Optional;

/**
 * UserRepository
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 30.06.2020
 */
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     */
    Optional<User> findByName(String name);

    /**
     * Find by name and password optional.
     *
     * @param name     the name
     * @param password the password
     * @return the optional
     */
    Optional<User> findByNameAndPassword(String name, String password);
}
