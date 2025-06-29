package ucr.ac.cr.CulturalEvent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.CulturalEvent.model.User;
import ucr.ac.cr.CulturalEvent.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;



    public User saveUser(User user) {
        return userRepository.save(user);
    }


    public List<User> findAllUser() {
        return this.userRepository.findAll();
    }


    public Optional<User> findUserById(Integer id) {
        return this.userRepository.findById(id);
    }



    public void deleteUser(Integer id) {
       this.userRepository.deleteById(id);
    }


    public User editUser(Integer id, User userEdit) {
        Optional<User> userOp=this.userRepository.findById(id);
        if(userOp.isPresent()){
            User user=userOp.get();
            user=userEdit;

            return this.userRepository.save(user);
        }
        return null;
    }

    public Optional<User> loginByEmail (String email, String password){
        return userRepository.findByEmailAndPassword(email,password);
    }


    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public boolean isOrganizer(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent() && "organizer".equals(user.get().getProfile());
    }
}
