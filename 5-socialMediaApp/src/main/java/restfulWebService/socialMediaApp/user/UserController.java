package restfulWebService.socialMediaApp.user;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.validation.Valid;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
public class UserController {

    private UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @RequestMapping(path = "/users", method=RequestMethod.GET)
    public ArrayList<User> getAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        var user= this.userDaoService.findUserById(id);
        if (user == null) {
            throw new UserNotFoundException("user not found with id: " + id);
        }

        return user;
    }

    @PostMapping("users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        var savedUser= this.userDaoService.save(user);
        // Apply HATEOAS principles
        // Instead of returning the user object directly, we can return a ResponseEntity with a location header
        // This location header will point to the newly created resource
        // For example, if the user has an ID of 1, the location header will be set to /users/1
        // return ResponseEntity.created(URI.create("/users/" + user.getId())).build();
        var location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        var isDeleted= this.userDaoService.deleteById(id);
        if (!isDeleted) {
            throw new UserNotFoundException(String.format("can not delete, user not found with id %d",id));
        }
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("users/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody User user) {
        User updatedUser= this.userDaoService.updateUser(id, user);
        if (updatedUser == null) {
            throw new UserNotFoundException(String.format("can not update, user not found with id %d",id));
        }
        
        return ResponseEntity.noContent().build();
    }
    
    
    
}
