package restfulWebService.socialMediaApp.user;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL) // This will exclude null fields from the JSON response
// This annotation is used to control the serialization of the User class to JSON.
//// It ensures that only non-null fields are included in the JSON representation of the User object.
public class User {
    
    private int id;
    @Size(min = 2, message = "Name must be at least 2 characters")
    private String name;
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    
    public User() {
        super();
    }
    public User(int id, String name, LocalDate dateOfBirth) {
        this();
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
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
    
    @Override
    public String toString() {
        return "user [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + "]";
    }
    
}
