package ucr.ac.cr.CulturalEvent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucr.ac.cr.CulturalEvent.model.Event;

public interface EventRepository extends JpaRepository <Event, Integer> {
}
