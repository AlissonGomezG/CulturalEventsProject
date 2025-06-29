package ucr.ac.cr.CulturalEvent.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (name = "event_name", nullable = false, length = 100)
    private String eventName;
    @Column (name = "date", nullable = false, length = 50)
    private String date;
    @Column (name = "location", nullable = false, length = 100)
    private String location;
    @Column (name = "time", nullable = false, length = 50)
    private String time;
    @Column (name = "description", nullable = true, length = 500)
    private String description;
    @Column (name = "available_space", nullable = false)
    private Integer availableSpace;

    public Event() {
    }

    public Event(Integer id, String eventName, String date, String location, String time, String description, Integer availableSpace) {
        this.id = id;
        this.eventName = eventName;
        this.date = date;
        this.location = location;
        this.time = time;
        this.description = description;
        this.availableSpace = availableSpace;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAvailableSpace() {
        return availableSpace;
    }

    public void setAvailableSpace(Integer availableSpace) {
        this.availableSpace = availableSpace;
    }

}
