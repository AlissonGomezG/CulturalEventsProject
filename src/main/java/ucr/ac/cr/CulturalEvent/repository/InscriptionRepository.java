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

    List<Inscription> findByUserId(Integer userId);

    List<Inscription> findByEventId(Integer eventId);

    Optional<Inscription> findByUserIdAndEventId(Integer userId, Integer eventId);

    boolean existsByUserIdAndEventIdAndStatus(Integer userId, Integer eventId, String status);

    @Query("SELECT COUNT(i) FROM Inscription i WHERE i.event.id = :eventId AND i.status = 'ACTIVE'")
    Integer countActiveInscriptionsByEventId(@Param("eventId") Integer eventId);

    List<Inscription> findByUserIdAndStatus(Integer userId, String status);

    List<Inscription> findByEventIdAndStatus(Integer eventId, String status);
}
