package ru.job4j.forum.control;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * PosPOSTControlTest
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 06.07.2020
 */
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class PosPOSTControlTest {

    @MockBean
    private PostService posts;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void shouldAddPostAndRedirectOnPost() throws Exception {
        this.mockMvc.perform(post("/create")
                .param("name", "Куплю ладу-грант. Дорого.")
                .param("description", "Срочно.")
                .param("author", "admin"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).addPost(argument.capture());
        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
        assertThat(argument.getValue().getAuthor().getName(), is("admin"));
        assertThat(argument.getValue().isTopic(), is(true));
    }

    @Test
    @WithMockUser
    void shouldUpdatePostAndRedirectOnPost() throws Exception {
        when(posts.getTopic(any(Post.class))).thenCallRealMethod();
        this.mockMvc.perform(
                post("/create")
                        .param("name", "Продам ладу-грант. Дорого.")
                        .param("description", "Очень Срочно.")
                        .param("author", "user")
                        .param("oldPostId", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).updatePost(argument.capture());
        assertThat(argument.getValue().getName(), is("Продам ладу-грант. Дорого."));
        assertThat(argument.getValue().getDescription(), is("Очень Срочно."));
        assertThat(argument.getValue().getId(), is(1L));
        assertThat(argument.getValue().getAuthor().getName(), is("user"));
    }

    @Test
    @WithMockUser
    void shouldAddPostAnswerAndRedirectOnPost() throws Exception {
        this.mockMvc.perform(post("/create")
                .param("name", "")
                .param("description", "Супер описание.")
                .param("answerFor", "1")
                .param("author", "user"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).addPost(argument.capture());
        assertThat(argument.getValue().getName(), is(""));
        assertThat(argument.getValue().getDescription(), is("Супер описание."));
        assertThat(argument.getValue().isTopic(), is(false));
        assertThat(argument.getValue().getAuthor().getName(), is("user"));
    }
}
