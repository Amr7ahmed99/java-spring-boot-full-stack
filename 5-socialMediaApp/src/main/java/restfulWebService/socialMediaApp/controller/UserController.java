package restfulWebService.socialMediaApp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import jakarta.validation.Valid;
import restfulWebService.socialMediaApp.Dtos.GetUserDTO;
import restfulWebService.socialMediaApp.Dtos.PartialUserUpdateDTO;
import restfulWebService.socialMediaApp.exception.UserNotFoundException;
import restfulWebService.socialMediaApp.models.Post;
import restfulWebService.socialMediaApp.models.User;
import restfulWebService.socialMediaApp.service.PostDaoService;
import restfulWebService.socialMediaApp.service.UserDaoService;
import restfulWebService.socialMediaApp.utils.ResponseMessage;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserDaoService userDaoService;
    private final ResponseMessage responseMessage;
    private final PostDaoService postDaoService;


    public UserController(UserDaoService userDaoService, ResponseMessage responseMessage, PostDaoService postDaoService) {
        this.userDaoService = userDaoService;
        this.responseMessage = responseMessage;
        this.postDaoService = postDaoService;
    }

    @GetMapping
    public ResponseEntity<MappingJacksonValue> getAllUsers() {
        List<User> users = this.userDaoService.findAll();

        MappingJacksonValue mapping = new MappingJacksonValue(users);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "dateOfBirth", "full_name");
        mapping.setFilters(new SimpleFilterProvider().addFilter("UserFilter", filter));
        return ResponseEntity.ok(mapping);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<GetUserDTO>> getUser(@PathVariable int id) {
        var user= this.userDaoService.findUserById(id);
        if (user == null) {
            String errMessage= this.responseMessage.getMessage("user.not.found", "user not found with id: " + id, id);
            throw new UserNotFoundException(errMessage);
        }
        // using spring HATEOAS to add links to the user entity
        // this allows the client to navigate to related resources easily
        // for example, we can add a link to get all users
        // this is useful for building RESTful APIs that are easy to use and navigate
        // and also to follow the HATEOAS principle (Hypermedia as the Engine of Application State)
        
        GetUserDTO getUserDto = new GetUserDTO(user.getId(), user.getName(), user.getDateOfBirth().toString(), user.getEmail());
        EntityModel<GetUserDTO> entityModel = EntityModel.of(getUserDto);
        WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).getAllUsers());
        entityModel.add(link.withRel("all-users"));

        return ResponseEntity.ok(entityModel);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        var savedUser= this.userDaoService.save(user);
        var location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        var isDeleted= this.userDaoService.deleteById(id);
        if (!isDeleted) {
            String errMessage= this.responseMessage.getMessage("failed.to.delete.user.not.found", "failed to delete, user not found with id: " + id, id);
            throw new UserNotFoundException(errMessage);
        }
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable int id, @Valid @RequestBody PartialUserUpdateDTO partialUserUpdateDTO) {
        User updatedUser= this.userDaoService.updateUser(id, partialUserUpdateDTO);
        if (updatedUser == null) {
            String errMessage= this.responseMessage.getMessage("failed.to.update.user.not.found", "failed to update, user not found with id: " + id, id);
            throw new UserNotFoundException(errMessage);
        }
        
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable int id) {

        // check if userId is valid
        User user= this.userDaoService.findUserById(id);
        if (user == null) {
            String errMessage= this.responseMessage.getMessage("user.not.found", "user not found with id: " + id, id);
            throw new UserNotFoundException(errMessage);
        }
        
        return ResponseEntity.ok(user.getPosts());
    }
    

    @PostMapping("/{id}/posts")
    public ResponseEntity<User> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        User user= this.userDaoService.findUserById(id);
        if (user == null) {
            String errMessage= this.responseMessage.getMessage("user.not.found", "user not found with id: " + id, id);
            throw new UserNotFoundException(errMessage);
        }
        post.setId(null);
        post.setUser(user);
        Post savedPost = this.postDaoService.save(post);
        var location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
