package restfulWebService.socialMediaApp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;
import restfulWebService.socialMediaApp.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
    
    @NativeQuery(value = "SELECT * FROM posts WHERE user_id = ?1")
    List<Post> findPostByUserId(int userId);
}