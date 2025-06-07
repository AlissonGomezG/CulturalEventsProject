package ucr.ac.cr.CulturalEvent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.CulturalEvent.model.User;
import ucr.ac.cr.CulturalEvent.repository.IRegisterUser;
import ucr.ac.cr.CulturalEvent.repository.UserRegister;

import java.util.List;
@Service
public class UserService implements IRegisterUser {

    @Autowired
    UserRegister userRegister;


    @Override
    public User saveUser(User user) {
        return this.userRegister.saveUser(user);
    }

    @Override
    public List<User> getAllUser() {
        return this.userRegister.getAllUser();
    }

    @Override
    public User getUser(Integer id) {
        return this.userRegister.getUser(id);
    }

    @Override
    public User deleteUser(Integer id) {
        return this.userRegister.deleteUser(id);
    }

    @Override
    public User editUser(Integer id, User userEdit) {
        return this.userRegister.editUser(id,userEdit);
    }

    public Boolean existId (Integer id){
        return this.userRegister.existId(id);
    }
}
