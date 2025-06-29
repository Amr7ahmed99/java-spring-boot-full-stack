package restfulWebService.socialMediaApp.user;

import java.time.LocalDate;

public class User {
    private int id;
    private String name;
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
