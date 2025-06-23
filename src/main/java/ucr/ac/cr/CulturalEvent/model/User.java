package ucr.ac.cr.CulturalEvent.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id",nullable = false,unique = true,length = 10)
    private Integer id;
    @Column(name ="name",nullable = false,length = 70)
    private String name;
    @Column(name ="email",nullable = false,length = 150)
    private String email;
    @Column(name ="telephone",nullable = false,length = 20)
    private String telephone;
    @Column(name ="profile",nullable = false,length = 20)
    private String profile;
    @Column(name ="password",nullable = false,length = 150)
    private String password;

    public User() {
    }

    public User(Integer id, String name, String email, String telephone, String profile, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.profile = profile;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
