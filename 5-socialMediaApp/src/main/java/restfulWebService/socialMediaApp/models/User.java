package restfulWebService.socialMediaApp.models;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL) // This will exclude null fields from the JSON response
// This annotation is used to control the serialization of the User class to JSON.
//// It ensures that only non-null fields are included in the JSON representation of the User object.
@JsonFilter("UserFilter") // This annotation is used to filter the fields of the User class during serialization
@Entity(name = "user_details") // This annotation is used to specify the name of the entity in the database
public class User {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    @Size(min = 2, message = "Name must be at least 2 characters")
    @JsonProperty("full_name") // This annotation is used to change the name of the field in the JSON response
    private String name;
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    @Email(message = "Invalid email format")
    private String email;
    // @JsonIgnore // This annotation is used to ignore the password field during serialization
    private String password;
    @OneToMany(mappedBy = "user") // This annotation is used to define a one-to-many relationship with the Post entity
    @JsonIgnore
    private List<Post> posts; // Assuming a User can have multiple posts

    public User() {
        super();
    }

    public User(int id, String name, LocalDate dateOfBirth, String email, String password) {
        this();
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Post> getPosts() {
        return posts;
    }
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    @Override
    public String toString() {
        return "user [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", password=" + password + "]";
    }
    
}
