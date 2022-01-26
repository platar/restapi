package info.lavait.restapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.lavait.restapi.model.Post;
import info.lavait.restapi.repository.PostRepository;
import liquibase.pro.packaged.A;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.util.AssertionErrors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.regex.Matcher;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class PostControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;

    @Test
    @Transactional // removed added recored after test
    void shouldGetSinglePost() throws Exception {
        // given
        Post newPost = new Post();
        newPost.setTitle("test");
        newPost.setContent("test content");
        newPost.setCreated(LocalDateTime.now());
        postRepository.save(newPost);

        // when
        MvcResult mvcResult = mockMvc.perform(get("/posts/" + newPost.getId()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
//                .andExpect(jsonPath("$.id", Matchers.is(1)));
        // then

        Post post = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Post.class);
        assertThat(post).isNotNull();
        assertThat(post.getId()).isEqualTo(newPost.getId());
        assertThat(post.getTitle()).isEqualTo("test");
        assertThat(post.getContent()).isEqualTo("test content");
//        assertThat(post.getComment()).hasSize(9);
    }


}