package ucr.ac.cr.CulturalEvent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.CulturalEvent.model.Event;
import ucr.ac.cr.CulturalEvent.repository.EventRepository;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event saveEvent(Event event) {
        return this.eventRepository.save(event);
    }

    public List<Event> findAllEvents() {
        return this.eventRepository.findAll();
    }

    public Optional<Event> findEventById(Integer id) {
        return this.eventRepository.findById(id);
    }

    public void deleteEvent(Integer id) {
        this.eventRepository.deleteById(id);
    }

    public Event editEvent(Integer id, Event eventEdit) {
        Optional<Event> eventOptional = this.eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            event = eventEdit;

            return this.eventRepository.save(event);
        }
        return null;
    }
}//end class