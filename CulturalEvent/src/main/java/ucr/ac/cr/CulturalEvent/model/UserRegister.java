/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.CulturalEvent.model;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *
 * @author Johan Brenes
 */
public class UserRegister {

    //atributos
    private GenericDAO_JSON<User> userDAO;

    //constructor
    public UserRegister() {
        Type type = new TypeToken<User[]>() {}.getType();
        userDAO = new GenericDAO_JSON<User>("users.json", type);
    }//endContructor

    //métodos
    public String getALL() {
        String out = "User List:\n";
        for (User user : userDAO.getAll()) {
            out += user + "\n";
        }//endFor
        return out;
    }//endGetAll

    public String add(User user) {
//        if (user.getId() == userDAO.findById(user)) {
//            return "The User with the id alredy exist!";
//        } else {
            if (userDAO.save(user)) {
                return "User added succesfully";
            }//endIF
            return "Error saving user";
//        }//endIf
    }//endAdd

    public String edit(User userEdit) {
        if (userDAO.update(userEdit)) {
            return "User edited succesfully";
        }//endIf
        return "Error editing user";
    }//endEdit

    public String delete(int id) {
        if (userDAO.remove(id)) {
            return "Succecsfully delete user";
        }//endIf
        return "Error delitng user";
    }//endDelete

    public User searchId(int id) {
        return userDAO.getElement(id);
    }//endSearchId

    public String[][] getMatrix() {
        ArrayList<User> users = userDAO.getAll();
        if (users == null || users.isEmpty()) {
            return new String[0][6];
        } else {
            String[][] matrixUser = new String[users.size()][6];
            for (int row = 0; row < matrixUser.length; row++) {
                for (int colum = 0; colum < matrixUser[0].length; colum++) {
                    matrixUser[row][colum] = users.get(row).setColumData(colum);
                }//endFor
            }//endFor
            return matrixUser;
        }//endIf

    }//endGetMatrix

}//endClass
