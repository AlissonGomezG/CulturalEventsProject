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
import javax.swing.event.MouseInputListener;
import ucr.ac.cr.CulturalEvent.model.User;
import ucr.ac.cr.CulturalEvent.model.UserRegister;
import ucr.ac.cr.CulturalEvent.view.DialogUser;
import ucr.ac.cr.CulturalEvent.view.FrmReport;
import ucr.ac.cr.CulturalEvent.view.PanelButton;

/**
 *
 * @author allis
 */
public class UserController implements ActionListener, MouseInputListener, KeyListener {

    //atributos
    private DialogUser dialogUser;
    private PanelButton panelButton;
    private FrmReport frmUser;
    private UserRegister userRegister;
    private int option = 0;
    private User userSearch = null;

    //controlador
    public UserController(UserRegister userRegister) {
        this.userRegister = userRegister;
        this.frmUser = new FrmReport();
        this.frmUser.setDataTable(userRegister.getMatrix(), User.LABELS_USER);
        this.frmUser.listenKey(this);
        this.frmUser.listenMouse(this);
        this.frmUser.setVisible(true);
        this.dialogUser = new DialogUser(this.frmUser, true);
        this.panelButton = this.frmUser.getPanelButton();
        this.panelButton.setListen(this);
        this.dialogUser.setListen(this);
    }//endControlador

    @Override
    public void actionPerformed(ActionEvent e) {
        frmUser.requestFocusInWindow();
        switch (e.getActionCommand()) {

            case "add":
                option = 1;
                dialogUser.setVisible(true);

                break;
            case "edit":
                option = 2;
                if (userSearch != null) {
                    dialogUser.setUser(userSearch);
                    dialogUser.setVisible(true);
                }//endIf
//                System.out.println("Presionó Edit");
                break;
            case "delete":
                if (userSearch != null) {
                    FrmReport.setMessage(userRegister.delete(userSearch.getId()));
                    frmUser.setDataTable(userRegister.getMatrix(), User.LABELS_USER);
                    userSearch = null;
                }//endIf
//                System.out.println("Presionó Delete");
                break;
            case "close":
                frmUser.dispose();
                break;

            case "search":
                System.out.println("search");
                dialogUser.setUser(userRegister.searchId(Integer.parseInt(dialogUser.getId())));
                dialogUser.setEnableID(false);
                break;

            case "ok":
                if (validation(dialogUser.getUser())) {
                    if (option == 1) {
                        FrmReport.setMessage(userRegister.add(dialogUser.getUser()));
                        dialogUser.clean();
                        dialogUser.dispose();
//                    System.err.println("save");
                    } else {
                        FrmReport.setMessage(userRegister.edit(dialogUser.getUser()));
                        dialogUser.clean();
                        dialogUser.dispose();
//                    System.err.println("Edit");
                    }//endIf
                }//endIf
                userSearch = null;
                frmUser.setDataTable(userRegister.getMatrix(), User.LABELS_USER);
                dialogUser.setEnableID(true);
                break;
            case "cancel":
                dialogUser.clean();
                dialogUser.setVisible(false);
                dialogUser.setEnableID(true);
                userSearch = null;
//                System.out.println("Presionó Cancel");
                break;
        }//endSwitch

    }//endActionPerformed

    public boolean validation(User user) {
        if (user.getId() == 0) {
            FrmReport.setMessage("The ID is missing");
            return false;
        } else if (user.getName().isEmpty()) {
            FrmReport.setMessage("The Name is missing");
            return false;
        } else if (user.getEmail().isEmpty()) {
            FrmReport.setMessage("The Email is missing");
            return false;
        } else if (user.getTelephone().isEmpty()) {
            FrmReport.setMessage("The Telephone is missing");
            return false;
        } else if (user.getUserType().isEmpty()) {
            FrmReport.setMessage("The User Type is missing");
            return false;
        } else {
            return true;
        }//endIf
    }//endValidation

    @Override
    public void mouseClicked(MouseEvent e) {
        frmUser.requestFocusInWindow();
        userSearch = new User();
        String[] userRow = frmUser.getRowSelected();
        userSearch.setId(Integer.parseInt(userRow[0]));
        userSearch.setName(userRow[1]);
        userSearch.setEmail(userRow[2]);
        userSearch.setTelephone(userRow[3]);
        userSearch.setUserType(userRow[4]);
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
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
