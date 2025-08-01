package restfulWebService.socialMediaApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

@Entity(name = "posts")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-incrementing primary key
    private Integer id;
    @Size(min = 5, message = "Content must be at least 5 characters")
    private String content;
    @ManyToOne(fetch = FetchType.LAZY) // Lazy loading to avoid fetching user details unless needed
    @JsonIgnore
    private User user;

    public Post() {
        super();
    }

    public Post(int id, String content, User user) {
        this();
        this.id = id;
        this.content = content;
        this.user = user;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", content=" + content + ", user=" + (user != null ? user.getId() : "null") + "]";
    }
}
