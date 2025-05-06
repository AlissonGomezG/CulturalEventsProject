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
     private Nodo frente;
    private Nodo fin;

    public WaitingUser() {
        this.frente = null;
        this.fin = null;
    }

    public void encolar(int userId) {
        Nodo nuevo = new Nodo(userId);
        if (frente == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
    }

    public int desencolar() {
        if (frente == null) {
            return -1; // o lanzar excepción
        }
        int userId = frente.getUserId();
        frente = frente.getSiguiente();
        if (frente == null) {
            fin = null;
        }
        return userId;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public String mostrarCola() {
        StringBuilder sb = new StringBuilder();
        Nodo actual = frente;
        while (actual != null) {
            sb.append("User ID: ").append(actual.getUserId()).append("\n");
            actual = actual.getSiguiente();
        }
        return sb.toString();
    }
}
