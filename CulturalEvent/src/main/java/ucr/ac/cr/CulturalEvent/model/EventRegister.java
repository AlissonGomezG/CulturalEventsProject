/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.CulturalEvent.model;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author allis
 */
public class EventRegister {

    private GenericDAO_JSON<Event> eventList;

    public EventRegister() {
        Type type = new TypeToken<Event[]>() {
        }.getType();
        this.eventList = new GenericDAO_JSON<Event>("event.json", type);
    }

    public String getAll() {

        String out = "Event  list: \n";
        for (Event event : this.eventList.getAll()) {
            out += event + "\n";
        }
        return out;
    }

    public String add(Event event) {

        if (this.eventList.save(event)) {

            return "The Event added successfully";
        }

        return "Error saving the event";
    }//fin del metodo add

    public String edit(Event eventEdit) {
        if (this.eventList.update(eventEdit)) {
            return "Event is successfully edited";
        }
        return "error editing the event!";
    }

    public String delete(int id) {

        if (this.eventList.remove(id)) {

            return "successfully deted event!";
        }
        return "error deleting event!";

    }//fin del metdo delete

    public Event searchById(int id) {

        return this.eventList.getElement(id);

    }//fin del metodo searchById

    public Event searchId(int id) {
        List<Event> events = this.eventList.getAll();
        for (Event event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        return new Event();
    }//endSearchId

    public String[][] getMatrix() {
        ArrayList<Event> events = this.eventList.getAll();
        if (events == null || events.isEmpty()) {
            return new String[0][6];
        } else {
            //crear la matriz
            String[][] matrixUser = new String[events.size()][6];
            //llenar la matriz
            for (int row = 0; row < matrixUser.length; row++) {
                for (int colum = 0; colum < matrixUser[0].length; colum++) {
                    matrixUser[row][colum] = events.get(row).setColumData(colum);
                }
            }
            return matrixUser;
        }
    }//endGetMatrix

}//end class
