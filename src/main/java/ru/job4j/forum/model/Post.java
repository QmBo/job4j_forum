package ru.job4j.forum.model;

import javax.persistence.*;
import java.util.*;

/**
 * Post
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 26.06.2020
 */
@Entity
@Table(name = "posts")
public class Post implements Comparable<Post> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private Calendar created = new GregorianCalendar();
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(foreignKeyDefinition = "USER_ID_FK"))
    private User author;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Post.class, mappedBy = "topicPost")
    private List<Post> answers;
    private boolean topic;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(foreignKeyDefinition = "POST_ID_FK"))
    private Post topicPost;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
    public Post setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Sets answers.
     *
     * @param answers the answers
     * @noinspection unused
     */
    public void setAnswers(List<Post> answers) {
        this.answers = answers;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public Post setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Gets desc.
     *
     * @return the desc
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets desc.
     *
     * @param desc the desc
     * @return the desc
     */
    public Post setDescription(String desc) {
        this.description = desc;
        return this;
    }

    /**
     * Gets created.
     *
     * @return the created
     */
    public Calendar getCreated() {
        return created;
    }

    /**
     * Sets created.
     *
     * @param created the created
     * @return the created
     * @noinspection unused
     */
    public Post setCreated(Calendar created) {
        this.created = created;
        return this;
    }

    /**
     * Gets author.
     *
     * @return the author
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author
     * @return the author
     */
    public Post setAuthor(User author) {
        this.author = author;
        return this;
    }

    /**
     * Gets answers.
     *
     * @return the answers
     */
    public List<Post> getAnswers() {
        this.answers.sort(Post::compareTo);
        return this.answers;
    }

    /**
     * Is topic boolean.
     *
     * @return the boolean
     */
    public boolean isTopic() {
        return topic;
    }

    /**
     * Sets topic.
     *
     * @param topic the topic
     * @return the topic
     * @noinspection UnusedReturnValue
     */
    public Post setTopic(boolean topic) {
        this.topic = topic;
        return this;
    }

    /**
     * Gets topic post.
     *
     * @return the topic post
     */
    public Post getTopicPost() {
        return topicPost;
    }

    /**
     * Sets topic post.
     *
     * @param topicPost the topic post
     */
    public void setTopicPost(Post topicPost) {
        this.topicPost = topicPost;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(id, post.id)
                && Objects.equals(name, post.name)
                && Objects.equals(description, post.description)
                && Objects.equals(created, post.created)
                && Objects.equals(author, post.author)
                && Objects.equals(topic, post.topic)
                && Objects.equals(topicPost, post.topicPost);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, created, author, topic, topicPost);
    }

    @Override
    public int compareTo(Post o) {
        return this.created.compareTo(o.getCreated());
    }
}
