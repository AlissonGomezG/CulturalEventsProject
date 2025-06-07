package ucr.ac.cr.CulturalEvent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.CulturalEvent.model.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}") {
        public ResponseEntity<?> getEvent(@PathVariable Integer id){
            Event event = eventService.getEvent(id);
            if (event == null || event.getId() == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El evento " + id + " no se encuentra");
            }
            return ResponseEntity.ok(event);
        }
    }

    @PostMapping
    public ResponseEntity<?> saveEvent(@Validated @RequestBody Event event, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        if (eventService.existId(event.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El evento " + event.getId() + " ya se encuntra registrado");
        }
        Event saveEvent = tareaService.saveEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveEvent);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteEvent(@PathVariable Integer id) {
        if (eventService.existId(id)) {
            eventService.deleteEvent(id);
            return ResponseEntity.status(HttpStatus.OK).body("El evento " + id + " fue eliminada con exito");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("El evento " + id + " no se encuentra registrado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editEvent(@Validated @PathVariable Integer id, @RequestBody Event editEvent, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        if (eventService.existId(id)) {
            if (id!= editEvent.getId()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("El identificador de la tarea no es igual al del objeto");
            } else {
                return ResponseEntity.ok(eventService.editEvent(id, editEvent));
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("La tarea " + id + " no está registrada");
    }
}//end class
