package ucr.ac.cr.CulturalEvent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.CulturalEvent.model.Inscription;

import java.util.List;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {

    List<Inscription> findByEventId(Integer eventId);

    List<Inscription> findByEventIdAndStatus(Integer eventId, String status);

    List<Inscription> findByUserId(Integer userId);
}//end class
