package restfulWebService.socialMediaApp.user;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.Locale;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
@RequestMapping("/users")
public class UserController {

    private final UserDaoService userDaoService;

    private final MessageSource messageSource;

    public UserController(UserDaoService userDaoService, MessageSource messageSource) {
        this.userDaoService = userDaoService;
        this.messageSource = messageSource;
    }

    @GetMapping
    public ArrayList<User> getAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        var user= this.userDaoService.findUserById(id);
        if (user == null) {
            String errMessage= getMessage("user.not.found", "user not found with id: " + id, id);
            throw new UserNotFoundException(errMessage);
        }

        return user;
    }

    @PostMapping
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        var isDeleted= this.userDaoService.deleteById(id);
        if (!isDeleted) {
            String errMessage= getMessage("failed.to.delete.user.not.found", "failed to delete, user not found with id: " + id, id);
            throw new UserNotFoundException(errMessage);
        }
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody User user) {
        User updatedUser= this.userDaoService.updateUser(id, user);
        if (updatedUser == null) {
            String errMessage= getMessage("failed.to.update.user.not.found", "failed to update, user not found with id: " + id, id);
            throw new UserNotFoundException(errMessage);
        }
        
        return ResponseEntity.noContent().build();
    }

    private String getMessage(String key, String defaultMessage, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, args, defaultMessage, locale);
    }
    
}
