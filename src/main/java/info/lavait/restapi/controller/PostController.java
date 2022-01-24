package info.lavait.restapi.controller;

import info.lavait.restapi.controller.dto.PostDto;
import info.lavait.restapi.model.Post;
import info.lavait.restapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public List<PostDto> getPosts(@RequestParam(required = false) int page) {
        int pageNumber = page >= 0 ? page : 0;
        return PostDtoMapper.mapToPostDtos(postService.getPosts(pageNumber));
    }

    @GetMapping("/posts/{postId}")
    public Post getPost(@PathVariable("postId") Long postId) {
        return postService.getPost(postId);
    }
}
