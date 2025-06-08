package ucr.ac.cr.CulturalEvent.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.CulturalEvent.model.User;
import ucr.ac.cr.CulturalEvent.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Listar todos los usuarios
    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    //Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User saved = userService.save(user);
        return ResponseEntity.ok(saved);
    }

    //Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody User user) {
        return userService.update(id, user)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = userService.delete(id);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}//end class
