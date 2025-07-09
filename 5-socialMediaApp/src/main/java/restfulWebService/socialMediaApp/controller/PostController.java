package restfulWebService.socialMediaApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restfulWebService.socialMediaApp.exception.UserNotFoundException;
import restfulWebService.socialMediaApp.models.Post;
import restfulWebService.socialMediaApp.models.User;
import restfulWebService.socialMediaApp.service.PostDaoService;
import restfulWebService.socialMediaApp.service.UserDaoService;
import restfulWebService.socialMediaApp.utils.ResponseMessage;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostDaoService postDaoService;
    private final UserDaoService userDaoService;
    private final ResponseMessage responseMessage;

    
    public PostController(PostDaoService postDaoService, UserDaoService userDaoService, ResponseMessage responseMessage) {
        this.postDaoService = postDaoService;
        this.userDaoService = userDaoService;
        this.responseMessage = responseMessage;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getUserPosts(@RequestParam int userId) {

        // check if userId is valid
        User user= this.userDaoService.findUserById(userId);
        if (user == null) {
            String errMessage= this.responseMessage.getMessage("invalid.user.id", "invalid user id: " + userId, userId);
            throw new UserNotFoundException(errMessage);
        }

        List<Post> posts = this.postDaoService.findPostByUserId(userId);
        
        return ResponseEntity.ok(posts);
    }
}
