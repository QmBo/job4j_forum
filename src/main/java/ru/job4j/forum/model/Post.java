package ru.job4j.forum.model;

import java.util.*;

/**
 * Post
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 26.06.2020
 */
public class Post {
    private int id;
    private String name;
    private String desc;
    private Calendar created = new GregorianCalendar();
    private User author;
    private List<Post> answers = new LinkedList<>();
    private boolean topic = false;
    private Post topicPost;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
    public Post setId(int id) {
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
    public String getDesc() {
        return desc;
    }

    /**
     * Sets desc.
     *
     * @param desc the desc
     * @return the desc
     */
    public Post setDesc(String desc) {
        this.desc = desc;
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
        return answers;
    }

    /**
     * Add answer.
     *
     * @param answer the answer
     */
    synchronized public void  addAnswer(final Post answer) {
        answer.setTopicPost(this);
        this.answers.add(answer);
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
    private void setTopicPost(Post topicPost) {
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
        return id == post.id
                && topic == post.topic
                && Objects.equals(name, post.name)
                && Objects.equals(desc, post.desc)
                && Objects.equals(created, post.created)
                && Objects.equals(author, post.author)
                && Objects.equals(answers, post.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, created, author, answers, topic);
    }
}
