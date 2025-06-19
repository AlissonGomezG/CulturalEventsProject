package ucr.ac.cr.CulturalEvent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucr.ac.cr.CulturalEvent.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
