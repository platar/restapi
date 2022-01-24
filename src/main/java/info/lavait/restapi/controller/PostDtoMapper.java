package info.lavait.restapi.controller;

import info.lavait.restapi.controller.dto.PostDto;
import info.lavait.restapi.model.Post;

import java.util.List;
import java.util.stream.Collectors;

public class PostDtoMapper {

    private PostDtoMapper() {
    }

    public static List<PostDto> mapToPostDtos(List<Post> posts) {
        return posts.stream()
                .map(post -> PostDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .created(post.getCreated())
                        .build())
                .collect(Collectors.toList());
    }
}
