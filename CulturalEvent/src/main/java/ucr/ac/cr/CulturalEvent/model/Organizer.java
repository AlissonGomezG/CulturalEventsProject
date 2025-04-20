/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.CulturalEvent.model;

/**
 *
 * @author Johan Brenes
 */
public class Organizer extends User {

    private String biography;
    private String contact;
    private String events;

    public Organizer() {
        biography = "";
        contact = "";
        events = "";
    }

    public Organizer(String biography, String contact, String events, int id, String name, String email, String telephone, String userType) {
        super(id, name, email, telephone, userType);
        this.biography = biography;
        this.contact = contact;
        this.events = events;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Organizer{" + "biography=" + biography + ", contact=" + contact + ", events=" + events + '}';
    }

}//endOrganizer
