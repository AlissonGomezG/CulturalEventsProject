package ucr.ac.cr.CulturalEvent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.CulturalEvent.model.DTO.LoginDTO;
import ucr.ac.cr.CulturalEvent.model.User;
import ucr.ac.cr.CulturalEvent.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Integer id) {
       Optional <User> user = userService.findUserById(id);
        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario " + id + " no se encuentra");
        }
        return ResponseEntity.ok(user);
    }

   @PostMapping
   public ResponseEntity<?> saveUser(@Validated @RequestBody User user, BindingResult result) {
       if (result.hasErrors()) {
           Map<String, String> errors = new HashMap<>();
           for (FieldError error : result.getFieldErrors()) {
               errors.put(error.getField(), error.getDefaultMessage());
           }
           return ResponseEntity.badRequest().body(errors);
       }

       if (user.getId() != null) {
           Optional<User> userOp = userService.findUserById(user.getId());
           if (userOp.isPresent()) {
               return ResponseEntity.status(HttpStatus.CONFLICT)
                       .body("El usuario " + user.getId() + " ya se encuentra registrado");
           }
       }

       User savedUser = userService.saveUser(user);
       return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
   }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        Optional <User> userOp=this.userService.findUserById(id);
        if (!userOp.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El ID " + id + " No se encuentra registrado");
        }
        this.userService.deleteUser(id);
        return ResponseEntity.ok("Se eliminó el usuario con el id: " + id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> editUser(@Validated @PathVariable Integer id,
                                      @RequestBody User userEdit,
                                      BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        Optional<User> userOp = this.userService.findUserById(id);

        if (userOp.isPresent()) {
            if (id != userEdit.getId()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("El id del usuario no es igual al del objeto");
            } else {
                return ResponseEntity.ok(this.userService.editUser(id, userEdit));
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("El id no se encuentra registrado");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginByEmail(@RequestBody LoginDTO loginDTO) {
        Optional<User> userOp = userService.loginByEmail(loginDTO.getEmail(), loginDTO.getPassword());

        if (userOp.isPresent()) {

            User user = userOp.get();

            LoginDTO response = new LoginDTO(
                    "¡Bienvenido al sistema de reservación de espacios creativos!",
                    user.getEmail(),
                    user.getPassword(),
                    user.getProfile()
            );

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Credenciales incorrectos!");
        }
    }

}//end class

