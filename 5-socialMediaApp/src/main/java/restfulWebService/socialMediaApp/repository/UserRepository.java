package restfulWebService.socialMediaApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restfulWebService.socialMediaApp.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
