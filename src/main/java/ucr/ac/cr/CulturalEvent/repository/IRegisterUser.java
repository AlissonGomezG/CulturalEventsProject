package ucr.ac.cr.CulturalEvent.repository;

import ucr.ac.cr.CulturalEvent.model.User;

import java.util.List;

public interface IRegisterUser {
   public User saveUser (User user);
   public List<User> getAllUser ();
   public User getUser(Integer id);
   public User deleteUser (Integer id);
   public User editUser (Integer id, User userEdit);
}
