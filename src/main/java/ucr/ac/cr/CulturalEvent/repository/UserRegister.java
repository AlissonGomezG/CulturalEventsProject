package ucr.ac.cr.CulturalEvent.repository;

import org.springframework.stereotype.Repository;
import ucr.ac.cr.CulturalEvent.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRegister implements IRegisterUser{

    private ArrayList<User> listaUser;

    public UserRegister() {
        this.listaUser = new ArrayList<>();
    }

    @Override
    public User saveUser(User user) {
        this.listaUser.add(user);
        return user;
    }//fin del metodo save

    @Override
    public List<User> getAllUser() {
        return listaUser;
    }//fin del metodo getAllUser

    @Override
    public User getUser(Integer id) {
        for (int i = 0; i <this.listaUser.size(); i++) {
            if (this.listaUser.get(i).getId()==id){
                return this.listaUser.get(i);
            }

        }
        return new User();
    }//fin del metodo getUser

    @Override
    public User deleteUser(Integer id) {
        for (int i = 0; i <this.listaUser.size(); i++) {
            if (this.listaUser.get(i).getId() == id) {
                User u = this.listaUser.get(i);
                this.listaUser.remove(i);
                return u;
            }
        }
        return null;
    }//fin del metodo delete

    @Override
    public User editUser(Integer id, User userEdit) {
            for (int i = 0; i <this.listaUser.size(); i++) {
                if (this.listaUser.get(i).getId() == id) {
                    listaUser.set(i, userEdit);
                    return listaUser.get(i);
                }
            }
        return new User();
    }//fin del metodo edit

    public Boolean existId (Integer id){
        for (int i = 0; i <this.listaUser.size(); i++) {
            if (this.listaUser.get(i).getId() == id) {
                return true;
            }
            }
            return false;
    }//fin de metodo existId


}//fin de la clase
