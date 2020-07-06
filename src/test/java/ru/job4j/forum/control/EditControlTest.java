package ru.job4j.forum.control;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * EditControlTest
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 03.07.2020
 */
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class EditControlTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @Test
    void shouldReturnErrorMessage() throws Exception {
        this.mockMvc.perform(get("/create?error=true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("errorMessage", "Topic create error."))
                .andExpect(view().name("edit"));
    }

    @Test
    void shouldReturnAnswerForMessage() throws Exception {
        this.mockMvc.perform(get("/create?answerFor=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("answerFor"))
                .andExpect(view().name("edit"));
    }

    @Test
    void shouldReturnUpdateMessage() throws Exception {
        this.mockMvc.perform(get("/create?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("oldPost"))
                .andExpect(view().name("edit"));
    }
}
