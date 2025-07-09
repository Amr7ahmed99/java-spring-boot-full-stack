package restfulWebService.socialMediaApp.Dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = false) // <-- Reject unknown fields
public class PartialUserUpdateDTO {

    @Size(min = 2, message = "Full name must be at least 2 characters")
    private String fullName;
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    @Email(message = "Invalid email format")
    private String email;

    public PartialUserUpdateDTO() {
        super();
    }
    public PartialUserUpdateDTO(String fullName, LocalDate dateOfBirth, String email) {
        this();
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
}
