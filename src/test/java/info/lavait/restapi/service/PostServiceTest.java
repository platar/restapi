package info.lavait.restapi.service;

import info.lavait.restapi.model.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WithMockUser
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    void shouldGetSinglePost() {
        // given
        // when
        Post singlePost = postService.getPost(1L);

        // then
        assertThat(singlePost).isNotNull();
        assertThat(singlePost.getId()).isEqualTo(1L);
    }

}