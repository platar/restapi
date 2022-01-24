package info.lavait.restapi.service;

import info.lavait.restapi.model.Post;
import info.lavait.restapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private static final int SIZE = 20;

    @Autowired
    private PostRepository postRepository;

    public List<Post> getPosts(int page){
        return postRepository.findAllPosts(PageRequest.of(page, SIZE));
    }

    public Post getPost(Long id){
        return postRepository.findById(id)
                .orElseThrow();
    }
}
