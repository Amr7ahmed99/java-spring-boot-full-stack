package restfulWebService.socialMediaApp.user;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import jakarta.validation.Valid;
import restfulWebService.socialMediaApp.utils.ResponseMessage;
import java.util.ArrayList;
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

    public UserController(UserDaoService userDaoService, ResponseMessage responseMessage) {
        this.userDaoService = userDaoService;
        this.responseMessage = responseMessage;
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
    public ResponseEntity<MappingJacksonValue> getUser(@PathVariable int id) {
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
        
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).getAllUsers());
        entityModel.add(link.withRel("all-users"));


        MappingJacksonValue mapping= new MappingJacksonValue(entityModel);
        SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("id", "full_name", "dateOfBirth", "email");
        mapping.setFilters(new SimpleFilterProvider().addFilter("UserFilter", filter));

        
        return ResponseEntity.ok(mapping);
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
    public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody User user) {
        User updatedUser= this.userDaoService.updateUser(id, user);
        if (updatedUser == null) {
            String errMessage= this.responseMessage.getMessage("failed.to.update.user.not.found", "failed to update, user not found with id: " + id, id);
            throw new UserNotFoundException(errMessage);
        }
        
        return ResponseEntity.noContent().build();
    }

    
    
}
