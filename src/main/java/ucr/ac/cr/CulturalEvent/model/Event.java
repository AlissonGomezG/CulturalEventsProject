package ucr.ac.cr.CulturalEvent.model;

public class Event {

    private Integer id;
    private String eventName;
    private String date;
    private String location;
    private String time;
    private String address;
    private Double price;
    private String description;
    private String organizer;
    private Integer availableSpace;

    public Event() {
        id=0;
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
