/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ucr.ac.cr.CulturalEvent;

import ucr.ac.cr.CulturalEvent.controller.UserController;
import ucr.ac.cr.CulturalEvent.model.UserRegister;

/**
 *
 * @author allis
 */
public class CulturalEvent {
    
    public static void main(String[] args) {
       UserRegister userRegister = new UserRegister();
       new UserController(userRegister);
       
    }//endMain
}//endClass
