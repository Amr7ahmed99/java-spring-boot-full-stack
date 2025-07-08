package restfulWebService.socialMediaApp.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class UserDaoService {
    // This class will handle the data access logic for User entities.
    // It will interact with the database to perform CRUD operations.

    private static ArrayList<User> users= new ArrayList<>();
    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount, "John Doe", LocalDate.of(1990, 1, 1), "email@gmail.com", "123456"));
        users.add(new User(++usersCount, "Jane Smith", LocalDate.of(1992, 2, 2), "email@gmail.com", "123456"));
        users.add(new User(++usersCount, "Alice Johnson", LocalDate.of(1988, 3, 3), "email@gmail.com", "123456"));
    }

    public ArrayList<User> findAll() {
        return users;
    }

    public User findUserById(int id) {
        Predicate<? super User> predicate= user -> user.getId() == id;
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User save(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public boolean deleteById(int id) {
        Predicate<? super User> predicate= user -> user.getId() == id;
        return users.removeIf(predicate);
    }

    public User updateUser(int id, User user) {
        Predicate<? super User> predicate= rec -> rec.getId() == id;
        User existingUser = users.stream().filter(predicate).findFirst().orElse(null);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setDateOfBirth(user.getDateOfBirth());
        }
        return existingUser;
    }
}
