/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.CulturalEvent.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import ucr.ac.cr.CulturalEvent.model.Event;
import ucr.ac.cr.CulturalEvent.model.EventRegister;
import ucr.ac.cr.CulturalEvent.view.DialogEvent;
import ucr.ac.cr.CulturalEvent.view.FrmReport;
import ucr.ac.cr.CulturalEvent.view.PanelButton;

/**
 *
 * @author allis
 */
public class EventController implements ActionListener, MouseListener, KeyListener {

    private DialogEvent dialogEvent;
    private PanelButton panelButton;
    private FrmReport frmEvent;
    private EventRegister eventRegister;
    private int option = 0;
    private Event eventSearch = null;

    public EventController(EventRegister eventRegister) {
        this.eventRegister = eventRegister;
        this.frmEvent = new FrmReport();
        this.frmEvent.setDataTable(eventRegister.getMatrix(), Event.LABELS_EVENT);
        this.frmEvent.listenKey(this);
        this.frmEvent.listenMouse(this);
        this.frmEvent.setVisible(true);
        this.dialogEvent = new DialogEvent(this.frmEvent, true);
        this.panelButton = this.frmEvent.getPanelButton();
        this.panelButton.setListen(this);
        this.dialogEvent.setListen(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        frmEvent.requestFocusInWindow();
        switch (e.getActionCommand()) {

            case "add":
                option = 1;
                dialogEvent.setVisible(true);

                break;
            case "edit":
                option = 2;
                if (eventSearch != null) {
                    dialogEvent.setEvent(eventSearch);
                    dialogEvent.setVisible(true);
                }
                break;

            case "delete":
                if (eventSearch != null) {
                    FrmReport.setMessage(eventRegister.delete(eventSearch.getId()));
                    frmEvent.setDataTable(eventRegister.getMatrix(), Event.LABELS_EVENT);
                    eventSearch = null;
                }

                break;
            case "close":
                frmEvent.dispose();
                break;

            case "search":
                dialogEvent.setEvent(eventRegister.searchId(Integer.parseInt(dialogEvent.getId())));
                dialogEvent.setEnableID(true);
                break;

            case "ok":
                if (this.validation(this.dialogEvent.getEvent())) {
                    if (option == 1) {
                        FrmReport.setMessage(this.eventRegister.add(this.dialogEvent.getEvent()));
                        this.dialogEvent.clean();
                        this.dialogEvent.dispose();
                    } else {
                        FrmReport.setMessage(this.eventRegister.edit(this.dialogEvent.getEvent()));
                        this.dialogEvent.clean();
                        this.dialogEvent.setEnableID(true);
                        this.dialogEvent.dispose();
                    }
                }
                eventSearch = null;
                frmEvent.setDataTable(eventRegister.getMatrix(), Event.LABELS_EVENT);
                dialogEvent.setEnableID(true);
                dialogEvent.dispose();
                break;
            case "cancel":
                dialogEvent.clean();
                dialogEvent.setEnableID(true);
                dialogEvent.dispose();
                eventSearch = null;

                break;
        }//finDelSwitch

    }

    public boolean validation(Event event) {
        if (event.getId() == 0) {
            FrmReport.setMessage("The ID is missing");
            return false;
        } else if (event.getEventName().isEmpty()) {
            FrmReport.setMessage("The event name is missing");
            return false;
        } else if (event.getLocation().isEmpty()) {
            FrmReport.setMessage("The location is missing");
            return false;
        } else if (event.getAddress().isEmpty()) {
            FrmReport.setMessage("The address Type is missing");
            return false;
        } else if (event.getAvailableSpace() == 0) {
            FrmReport.setMessage("The available space is missing");
            return false;
        } else if (event.getDate().isEmpty()) {
            FrmReport.setMessage("The date is missing");
            return false;
        } else if (event.getTime().isEmpty()) {
            FrmReport.setMessage("The time is missing");
            return false;
        } else if (event.getDescription().isEmpty()) {
            FrmReport.setMessage("The description is missing");
            return false;
        } else if (event.getOrganizerInformation().isEmpty()) {
            FrmReport.setMessage("The organizer information is missing");
            return false;
        } else if (event.getPrice() == 0.0) {
            FrmReport.setMessage("The price is missing");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        frmEvent.requestFocusInWindow();
        eventSearch = new Event();
        String[] userRow = frmEvent.getRowSelected();
        eventSearch.setId(Integer.parseInt(userRow[0]));
        eventSearch.setEventName(userRow[1]);
        eventSearch.setDate(userRow[2]);
        eventSearch.setLocation(userRow[3]);
        eventSearch.setTime(userRow[4]);
        eventSearch.setAddress(userRow[5]);
        eventSearch.setPrice(Double.parseDouble(userRow[6]));
        eventSearch.setDescription(userRow[7]);
        eventSearch.setOrganizerInformation(userRow[8]);
        eventSearch.setAvailableSpace(Integer.parseInt(userRow[9]));
    }

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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.frmEvent.requestFocusInWindow();
        this.frmEvent.filterByID();
    }
}
