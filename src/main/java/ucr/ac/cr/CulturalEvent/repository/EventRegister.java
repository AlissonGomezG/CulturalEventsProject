package ucr.ac.cr.CulturalEvent.repository;

import org.springframework.stereotype.Repository;
import ucr.ac.cr.CulturalEvent.model.Event;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventRegister implements IRegisterEvent {

    private ArrayList<Event> listEvent;

    public EventRegister() {
        this.listEvent = new ArrayList<>();
    }

    @Override
    public Event saveEvent(Event event) {
        this.listEvent.add(event);
        return event;
    }

    @Override
    public List<Event> getAllEvents() {
        return listEvent;
    }

    @Override
    public Event getEvent(Integer id) {
        for (int i = 0; i < this.listEvent.size(); i++) {
            if (this.listEvent.get(i).getId() == id) {
                return this.listEvent.get(i);
            }
        }
        return new Event();
    }

    @Override
    public Event deleteEvent(Integer id) {
        for (int i = 0; i < this.listEvent.size(); i++) {
            if (this.listEvent.get(i).getId() == id) {
                Event u = this.listEvent.get(i);
                this.listEvent.remove(i);
                return u;
            }
        }
        return null;
    }

    @Override
    public Event editEvent(Integer id, Event eventEdit) {
        for (int i = 0; i < this.listEvent.size(); i++) {
            if (this.listEvent.get(i).getId() == id) {
                listEvent.set(i, eventEdit);
                return listEvent.get(i);
            }
        }
        return new Event();
    }

    public Boolean existId(Integer id) {
        for (int i = 0; i < this.listEvent.size(); i++) {
            if (this.listEvent.get(i).getId() == id) {
                return true;
            }
        }
        return false;
    }

}
