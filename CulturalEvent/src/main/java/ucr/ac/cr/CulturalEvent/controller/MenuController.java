/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.CulturalEvent.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import ucr.ac.cr.CulturalEvent.model.Event;
import ucr.ac.cr.CulturalEvent.model.EventRegister;
import ucr.ac.cr.CulturalEvent.model.User;
import ucr.ac.cr.CulturalEvent.model.UserRegister;
import ucr.ac.cr.CulturalEvent.view.FrmMenu;
import ucr.ac.cr.CulturalEvent.view.FrmTblEvent;

/**
 *
 * @author allis
 */
public class MenuController implements ActionListener {

    private FrmMenu frmMenu;
    private UserRegister userRegister;
    private EventRegister eventRegister;

    public MenuController() {
        this.frmMenu = new FrmMenu();
        this.userRegister = new UserRegister();
        this.eventRegister = new EventRegister();
        this.frmMenu.listenMenu(this);
        this.frmMenu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "Exit":
                System.exit(0);
                break;

            case "UserRegister":
                new UserController(this.userRegister);
                break;

            case "EventRegister":
                new EventController(this.eventRegister);
                break;

            case "Reservation":

                String id = JOptionPane.showInputDialog("Enter your ID:");
                String userName = JOptionPane.showInputDialog("Enter your name:");

                if (userName.isEmpty() || id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You must enter a name and an ID to continue.");
                }

                try {
                    int userId = Integer.parseInt(id);
                    User user = userRegister.searchId(userId);

                    if (user == null || !user.getName().equalsIgnoreCase(userName)) {
                        JOptionPane.showMessageDialog(null, "The user is not registered. Please register first.");
                    }

                    // Si todo está bien, ahora  muestra la ventana de reservaciones
                    new ReservationController(userRegister, eventRegister);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "You must enter a valid number for the ID.");
                }
                break;

            case "ViewAvailableEvents":
                FrmTblEvent frmTblEvent = new FrmTblEvent();
                frmTblEvent.setDataTableCreative(this.eventRegister.getMatrix(), Event.LABELS_EVENT);
                frmTblEvent.setVisible(true);
                break;

        }
    }
}
