package ucr.ac.cr.CulturalEvent.repository;

import ucr.ac.cr.CulturalEvent.model.Event;

import java.util.List;

public interface IRegisterEvent {
    public Event saveEvent (Event event);
    public List<Event> getAllEvents ();
    public Event getEvent(Integer id);
    public Event deleteEvent (Integer id);
    public Event editEvent (Integer id, Event eventEdit);
}
