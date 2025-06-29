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
import ucr.ac.cr.CulturalEvent.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    private UserService userService;


    @GetMapping
    public List<Event> findAllEvents() {
        return eventService.findAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findEventById(@PathVariable Integer id) {
        Optional <Event> event = eventService.findEventById(id);
        if (!event.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El evento " + id + " no se encuentra");
        }
        return ResponseEntity.ok(event);
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
        if (event.getId() !=null) {

            Optional<Event> eventOptional = eventService.findEventById(event.getId());
            if (eventOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("El evento " + event.getId() + " ya se encuntra registrado");
            }
        }
        Event saveEvent = eventService.saveEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveEvent);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Integer id) {
        Optional<Event> eventOptional = this.eventService.findEventById(id);
        if (eventOptional.isPresent()) {
            this.eventService.deleteEvent(id);
            return ResponseEntity.status(HttpStatus.OK).body("El evento " + id + " fue eliminado con exito");
        }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El evento " + id + " no se encuentra registrado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editEvent(@Validated @PathVariable Integer id, @RequestBody Event editEvent, BindingResult result) {
        Optional<Event> eventOptional = this.eventService.findEventById(id);


        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }


        if (eventOptional.isPresent()) {
            if (id != editEvent.getId()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("El id del evento no es igual al del objeto");
            } else {
                return ResponseEntity.ok(this.eventService.editEvent(id, editEvent));
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("El evento " + id + " no está registrado");
    }


}//end class