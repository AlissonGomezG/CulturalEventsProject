/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.CulturalEvent.model;

/**
 *
 * @author allis
 */
public class User {
    private int id;
    private String name;
    private String email;
    private String telephone;
    private String userType;

    public User() {
        id=0;
        name="";
        email="";
        telephone="";
        userType="";
    }

    public User(int id, String name, String email, String telephone, String userType) {
        this.id = id;
        this.name = name;
        this.email=email;
        this.telephone = telephone;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + ", telephone=" + telephone + ", userType=" + userType + '}';
    }
    
    
    
    
}
