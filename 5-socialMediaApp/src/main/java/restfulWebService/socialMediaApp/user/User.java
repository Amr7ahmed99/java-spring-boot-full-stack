package restfulWebService.socialMediaApp.user;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL) // This will exclude null fields from the JSON response
// This annotation is used to control the serialization of the User class to JSON.
//// It ensures that only non-null fields are included in the JSON representation of the User object.
@JsonFilter("UserFilter") // This annotation is used to filter the fields of the User class during serialization
public class User {
    
    private int id;
    @Size(min = 2, message = "Name must be at least 2 characters")
    @JsonProperty("full_name") // This annotation is used to change the name of the field in the JSON response
    private String name;
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    private String email;
    // @JsonIgnore // This annotation is used to ignore the password field during serialization
    private String password;

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
    @Override
    public String toString() {
        return "user [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", password=" + password + "]";
    }
    
}
