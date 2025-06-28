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
    @Column (name = "address", nullable = false, length = 150)
    private String address;
    @Column (name = "price", nullable = false)
    private Double price;
    @Column (name = "description", nullable = true, length = 500)
    private String description;
    @Column (name = "organizer", nullable = false, length = 100)
    private String organizer;
    @Column (name = "available_space", nullable = false)
    private Integer availableSpace;

    public Event() {
    }

    public Event(Integer id, String eventName, String date, String location, String time, String address, Double price, String description, String organizer, Integer availableSpace) {
        this.id = id;
        this.eventName = eventName;
        this.date = date;
        this.location = location;
        this.time = time;
        this.address = address;
        this.price = price;
        this.description = description;
        this.organizer = organizer;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public Integer getAvailableSpace() {
        return availableSpace;
    }

    public void setAvailableSpace(Integer availableSpace) {
        this.availableSpace = availableSpace;
    }

}
