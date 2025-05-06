/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.CulturalEvent.model;

/**
 *
 * @author Johan Brenes
 */
public class WaitingPila {
     private Nodo frente;
    private int longitud;

    public WaitingPila() {
        frente = null;
        longitud = 0;
    }

    public void push(int userId) {
        Nodo nodo = new Nodo(userId);
        nodo.setSiguiente(frente);
        frente = nodo;
        longitud++;
    }

    public int pop(){
         if (estaVacia()) {
            System.out.println("No hay datos");
        }
        int data = frente.getUserId();
        frente = frente.getSiguiente();
        longitud--;
        return data;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public String toString(){
        String WaitingUsers = "";
        Nodo nodo = frente;
        while (nodo!=null){
           WaitingUsers += "\n" +nodo.getUserId();
            nodo=nodo.getSiguiente();
        }
        return WaitingUsers;
    }
    
     public int getLongitud() {
        return longitud;
    }
}//endClass
