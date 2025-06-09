package ucr.ac.cr.CulturalEvent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.CulturalEvent.model.User;
import ucr.ac.cr.CulturalEvent.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Integer id) {
        User user = userService.getUser(id);
        if (user == null || !userService.existId(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El usuario " + id + " no se encuentra");
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
        if (userService.existId(user.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El usuario " + user.getId() + " ya se encuentra registrado");
        }
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        if (userService.existId(id)) {
            userService.deleteUser(id);
            return ResponseEntity.ok(
                    "El usuario " + id + " fue eliminado con éxito");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("El usuario " + id + " no se encuentra registrado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editUser(@Validated @PathVariable Integer id,
                                      @RequestBody User user,
                                      BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        if (userService.existId(id)) {
            if (!id.equals(user.getId())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("El id del usuario no es igual al del objeto");
            } else {
                User updatedUser = userService.editUser(id, user);
                return ResponseEntity.ok(updatedUser);
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("El usuario " + id + " no está registrado");
    }
}//end class
