package ucr.ac.cr.CulturalEvent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucr.ac.cr.CulturalEvent.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<User> findByEmailAndPassword (String email, String password);
    Optional<User> findByEmail(String email);
}
