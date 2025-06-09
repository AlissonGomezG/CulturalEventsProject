package ucr.ac.cr.CulturalEvent.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.CulturalEvent.model.Event;
import ucr.ac.cr.CulturalEvent.repository.EventRegister;
import ucr.ac.cr.CulturalEvent.repository.IRegisterEvent;

import java.util.List;

@Service
public class EventService implements IRegisterEvent {


    @Autowired
    EventRegister eventRegister;


    @Override
    public Event saveEvent(Event event) {
        return this.eventRegister.saveEvent(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return this.eventRegister.getAllEvents();
    }

    @Override
    public Event getEvent(Integer id) {
        return this.eventRegister.getEvent(id);
    }

    @Override
    public Event deleteEvent(Integer id) {
        return this.eventRegister.deleteEvent(id);
    }

    @Override
    public Event editEvent(Integer id, Event eventEdit) {
        return this.eventRegister.editEvent(id, eventEdit);
    }

    public Boolean existId(Integer id) {
        return this.eventRegister.existId(id);
    }
}