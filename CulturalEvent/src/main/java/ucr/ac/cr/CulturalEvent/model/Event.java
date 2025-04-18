/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.CulturalEvent.model;

/**
 *
 * @author allis
 */
public class Event {
     private int id;
    private String eventName;
    private String date;
    private String location;
    private String time;
    private String address;
    private String price;
    private String description;
    private String organizerInformation;
    private int availableSpace;

    public Event() {
        id = 0;
        eventName = "";
        date = "";
        location = "";
        time = "";
        address = "";
        price = "";
        description = "";
        organizerInformation = "";
        availableSpace = 0;
    }

    public Event(int id, String eventName, String date, String location, String time, String address, String price, String description, String organizerInformation, int availableSpace) {
        this.id = id;
        this.eventName = eventName;
        this.date = date;
        this.location = location;
        this.time = time;
        this.address = address;
        this.price = price;
        this.description = description;
        this.organizerInformation = organizerInformation;
        this.availableSpace = availableSpace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganizerInformation() {
        return organizerInformation;
    }

    public void setOrganizerInformation(String organizerInformation) {
        this.organizerInformation = organizerInformation;
    }

    public int getAvailableSpace() {
        return availableSpace;
    }

    public void setAvailableSpace(int availableSpace) {
        this.availableSpace = availableSpace;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", eventName=" + eventName + ", date=" + date + ", location=" + location + ", time=" + time + ", address=" + address + ", price=" + price + ", description=" + description + ", organizerInformation=" + organizerInformation + ", availableSpace=" + availableSpace + '}';
    }
}
