package ucr.ac.cr.CulturalEvent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.CulturalEvent.model.Event;
import ucr.ac.cr.CulturalEvent.service.EventService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

package ucr.ac.cr.CulturalEvent.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.CulturalEvent.model.Event;
import ucr.ac.cr.CulturalEvent.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    //Listar todos los eventos
    @GetMapping
    public List<Event> getAll() {
        return eventService.findAll();
    }

    //Obtener un evento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Event> getById(@PathVariable Integer id) {
        return eventService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Crear un nuevo evento
    @PostMapping
    public ResponseEntity<Event> create(@RequestBody Event event) {
        Event saved = eventService.save(event);
        return ResponseEntity.ok(saved);
    }

    //Actualizar un evento existente
    @PutMapping("/{id}")
    public ResponseEntity<Event> update(@PathVariable Integer id, @RequestBody Event event) {
        return eventService.update(id, event)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Eliminar un evento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = eventService.delete(id);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}//end class
