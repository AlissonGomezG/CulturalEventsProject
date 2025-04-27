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
    private double price;
    private String description;
    private String organizerInformation;
    private int availableSpace;

    public static final String[] LABELS_EVENT = {" ID", "Event name", "Date", "Location", "Time", "Address", "Price", "Description", "Organizer information", "Available spaces"};

    public Event() {
        id = 0;
        eventName = "";
        date = "";
        location = "";
        time = "";
        address = "";
        price = 0.0;
        description = "";
        organizerInformation = "";
        availableSpace = 0;
    }

    public Event(int id, String eventName, String date, String location, String time, String address, double price, String description, String organizerInformation, int availableSpace) {
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

    public String setColumData(int colum) {
        switch (colum) {
            case 0:
                return String.valueOf(this.getId());
            case 1:
                return this.getEventName();
            case 2:
                return this.getDate();
            case 3:
                return this.getLocation();
            case 4:
                return this.getTime();
            case 5:
                return this.getAddress();
            case 6:
                return String.valueOf(this.getPrice());
            case 7:
                return this.getDescription();
            case 8:
                return this.getOrganizerInformation();
            case 9:
                return String.valueOf(this.getAvailableSpace());
        }
        return "";
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
}//endClass
