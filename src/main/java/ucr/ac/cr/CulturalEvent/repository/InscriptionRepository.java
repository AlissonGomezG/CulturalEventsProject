package ucr.ac.cr.CulturalEvent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.CulturalEvent.model.Inscription;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {

    List<Inscription> findByEventId(Integer eventId);

    List<Inscription> findByEventIdAndStatus(Integer eventId, String status);

}//end class
