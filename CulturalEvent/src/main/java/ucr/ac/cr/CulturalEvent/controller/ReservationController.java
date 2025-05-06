/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.CulturalEvent.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import ucr.ac.cr.CulturalEvent.model.Event;
import ucr.ac.cr.CulturalEvent.model.EventRegister;
import ucr.ac.cr.CulturalEvent.model.UserRegister;
import ucr.ac.cr.CulturalEvent.model.WaitingPila;
import ucr.ac.cr.CulturalEvent.view.FrmReservation;

/**
 *
 * @author allis
 */
public class ReservationController implements ActionListener, MouseListener {

    private FrmReservation frmReservation;
    private UserRegister userRegister;
    private EventRegister eventRegister;
    private Map<Integer, WaitingPila> waitingUser = new HashMap<>();

    public ReservationController(UserRegister userRegister, EventRegister eventRegister) {
        frmReservation = new FrmReservation();
        this.userRegister = userRegister;
        this.eventRegister = eventRegister;
        frmReservation.setCBoxUsers(this.userRegister.getCBoxUser());
        this.frmReservation.listenCBoxUsers(this);
        this.frmReservation.setTblEvent(this.eventRegister.getMatrix(), Event.LABELS_EVENT);
        this.frmReservation.listenMouseTbl(this);
        this.frmReservation.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "cBoxUsers":
                this.frmReservation.setUserLabels(this.userRegister.getUserByIndex(this.frmReservation.getCBoxUserIndex()));
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Event event = eventRegister.searchId(frmReservation.getRowIDSpace());

        try {
            int espacio = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of spaces to reserve"));

            if (espacio <= 0) {
                JOptionPane.showMessageDialog(frmReservation, "You must reserve at least one space.");
                return;
            }

            if (espacio > event.getAvailableSpace()) {
                // Si no hay suficientes espacios, se agrega al usuario a la pila de espera
                int userId = userRegister.getUserByIndex(frmReservation.getCBoxUserIndex()).getId();
                WaitingPila waitingPila = waitingUser.get(event.getId());

                if (waitingPila == null) {
                   waitingPila = new WaitingPila();
                   waitingUser.put(event.getId(), waitingPila);
                }

                waitingPila.push(userId); // Agregar el usuario a la pila
                frmReservation.waitingUsers(waitingPila.getLongitud());
                JOptionPane.showMessageDialog(frmReservation, "No available spaces. The user has been added to the waiting list.");
                return; // Terminar la ejecución
            }

            //Calculos
            double total = espacio * event.getPrice();
            int availableSpaces = event.getAvailableSpace() - espacio;

            // Actualizar el evento
            event.setAvailableSpace(availableSpaces);
            eventRegister.edit(event); // Se guarda el cambio en el JSON

            // Mostrar en la tabla de reservaciones
            frmReservation.addReserveTable(event, espacio, total);

            // Actualizar la tabla principal de eventos para reflejar los espacios disponibles
            frmReservation.setTblEvent(eventRegister.getMatrix(), Event.LABELS_EVENT);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frmReservation, "You must enter a valid number.");
        }
    }//end

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
