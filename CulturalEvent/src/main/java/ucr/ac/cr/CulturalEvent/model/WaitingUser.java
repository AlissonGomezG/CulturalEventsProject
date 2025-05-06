/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.CulturalEvent.model;

/**
 *
 * @author Johan Brenes
 */
public class WaitingUser {
    
    private WaitingPila waitingPila;

    public WaitingUser() {
        waitingPila = new WaitingPila();
    }
    
    public void save(int userId) {
        waitingPila.push(userId);
    }

    public int undo() {
        return waitingPila.pop();
    }

}//endClass
