package restfulWebService.socialMediaApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import restfulWebService.socialMediaApp.models.Post;
import restfulWebService.socialMediaApp.repository.PostRepository;

@Service
public class PostDaoService {
    private final PostRepository postRepository;
    
    public PostDaoService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findPostByUserId(int userId) {
        return this.postRepository.findPostByUserId(userId); 
    }

    public Post save(Post post) {
        return this.postRepository.save(post);
    }
}
