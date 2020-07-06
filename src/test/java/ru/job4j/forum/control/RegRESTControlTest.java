package ru.job4j.forum.control;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * RegRESTControlTest
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 03.07.2020
 */
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class RegRESTControlTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnTrueIfNameVacant() throws Exception {
        String response = this.mockMvc.perform(get("/reg/user222"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertThat(response, is("true"));
    }

    @Test
    void shouldReturnFalseIfNameNotVacant() throws Exception {
        String response = this.mockMvc.perform(get("/reg/user"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertThat(response, is("false"));
    }
}
