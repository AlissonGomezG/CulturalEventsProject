/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.CulturalEvent.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import ucr.ac.cr.CulturalEvent.model.Event;
import ucr.ac.cr.CulturalEvent.model.EventRegister;
import ucr.ac.cr.CulturalEvent.model.UserRegister;
import ucr.ac.cr.CulturalEvent.view.FrmReservation;

/**
 *
 * @author allis
 */
public class ReservationController implements ActionListener, MouseListener {

    private FrmReservation frmReservation;
    private UserRegister userRegister;
    private EventRegister eventRegister;

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

//    @Override
//    public void mouseClicked(MouseEvent e) {
//        Event event = this.eventRegister.searchId(this.frmReservation.getRowIDSpace());
//
//        int espacio = Integer.parseInt(JOptionPane.showInputDialog("Digite la cantidad de espacios a reservar"));
//        double total = espacio * event.getPrice();
//        int AvailableSpaces = event.getAvailableSpace() - espacio;
//        this.frmReservation.addReserveTable(event, AvailableSpaces, total);
//    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Event event = eventRegister.searchId(frmReservation.getRowIDSpace());

        try {
            int espacio = Integer.parseInt(JOptionPane.showInputDialog("Digite la cantidad de espacios a reservar"));

            if (espacio <= 0) {
                JOptionPane.showMessageDialog(frmReservation, "Debe reservar al menos un espacio.");
                return;
            }

            if (espacio > event.getAvailableSpace()) {
                JOptionPane.showMessageDialog(frmReservation, "No hay suficientes espacios disponibles.");
                return;
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
            JOptionPane.showMessageDialog(frmReservation, "Debe digitar un número válido.");
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
