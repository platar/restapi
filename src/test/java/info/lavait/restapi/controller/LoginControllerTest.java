package info.lavait.restapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.lavait.restapi.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;

    @Test
    void shouldLoginAndGetContent() throws Exception {
        MvcResult login = mockMvc.perform(post("/login")
        .content("{\"username\": \"test\", \"password\": \"test\"}"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        String token = login.getResponse().getHeader("Authorization");

        MvcResult secured = mockMvc.perform(get("/secured")
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(content().string("secured"))
                .andReturn();

        mockMvc.perform(get("/secured"))
                .andDo(print())
                .andExpect(status().is(401));

    }



}