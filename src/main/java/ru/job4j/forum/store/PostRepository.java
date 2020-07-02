package ru.job4j.forum.store;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Post;

import java.util.List;

/**
 * PostRepository
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 30.06.2020
 */
public interface PostRepository extends CrudRepository<Post, Long> {
    /**
     * Find by topic list.
     *
     * @param isTopic the is topic
     * @return the list
     */
    List<Post> findByTopic(Boolean isTopic);
}
