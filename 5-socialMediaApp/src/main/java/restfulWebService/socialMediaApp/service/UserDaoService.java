package restfulWebService.socialMediaApp.service;

import java.util.List;
import org.springframework.stereotype.Service;
import restfulWebService.socialMediaApp.Dtos.PartialUserUpdateDTO;
import restfulWebService.socialMediaApp.models.User;
import restfulWebService.socialMediaApp.repository.UserRepository;

@Service
public class UserDaoService {
    // This class will handle the data access logic for User entities.

    private UserRepository userRepository;

    public UserDaoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findUserById(int id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public boolean deleteById(int id) {
        this.findUserById(id);
        if (this.findUserById(id) == null) {
            return false;
        }
        this.userRepository.deleteById(id);
        return true;
    }

    public User updateUser(int id, PartialUserUpdateDTO partialUserUpdateDTO) {
        User fetchedUser= this.findUserById(id);
        if (fetchedUser == null) {
            return null; // User not found
        }
        User user = new User(
                fetchedUser.getId(),
                partialUserUpdateDTO.getFullName() != null ? partialUserUpdateDTO.getFullName() : fetchedUser.getName(),
                partialUserUpdateDTO.getDateOfBirth() != null ? partialUserUpdateDTO.getDateOfBirth() : fetchedUser.getDateOfBirth(),
                partialUserUpdateDTO.getEmail() != null ? partialUserUpdateDTO.getEmail() : fetchedUser.getEmail(),
                fetchedUser.getPassword() // Assuming password should not be changed
        );
        this.save(user);

        return user;
    }
}
