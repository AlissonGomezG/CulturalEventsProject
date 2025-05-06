/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.CulturalEvent.model;

/**
 *
 * @author Johan Brenes
 */
public class Nodo {
    private int userId;
    private Nodo siguiente;

    public Nodo(int userId) {
        this.userId = userId;
        this.siguiente = null;
    }

    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId){
        this.userId = userId;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}//endClass
