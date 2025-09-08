package ucr.ac.cr.CulturalEvent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.CulturalEvent.model.Event;
import ucr.ac.cr.CulturalEvent.model.Inscription;
import ucr.ac.cr.CulturalEvent.model.DTO.InscriptionDTO;
import ucr.ac.cr.CulturalEvent.model.User;
import ucr.ac.cr.CulturalEvent.service.EventService;
import ucr.ac.cr.CulturalEvent.service.InscriptionService;
import ucr.ac.cr.CulturalEvent.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/inscriptions")
@CrossOrigin(origins = "*")
public class InscriptionController {

    @Autowired
    private InscriptionService inscriptionService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<?> saveInscription(@RequestBody InscriptionDTO dto) {
        Optional<User> userOp = userService.findUserById(dto.getUserId());
        if (!userOp.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe");
        }

        Optional<Event> eventOp = eventService.findEventById(dto.getEventId());
        if (!eventOp.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El evento no existe");
        }

        Event event = eventOp.get();

        // Validar si la fecha del evento ya pasó
        LocalDate fechaEvento = LocalDate.parse(event.getDate());
        if (fechaEvento.isBefore(LocalDate.now())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El evento ya ocurrió");
        }

        // Validar si hay espacio disponible
        List<Inscription> activas = inscriptionService.findActiveByEventId(event.getId());
        if (activas.size() >= event.getAvailableSpace()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No hay cupo disponible");
        }

        // Validar si ya está inscrito
        for (Inscription ins : activas) {
            if (ins.getUser().getId().equals(dto.getUserId())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya estás inscrito");
            }
        }

        // Crear inscripción
        String fechaActual = LocalDateTime.now().toString();
        Inscription inscripcion = new Inscription(null, userOp.get(), event, fechaActual, "ACTIVE");

        Inscription guardada = inscriptionService.saveInscription(inscripcion);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }

    @GetMapping
    public List<Inscription> getAll() {
        return inscriptionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Optional<Inscription> op = inscriptionService.findById(id);
        if (!op.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inscripción no encontrada");
        }
        return ResponseEntity.ok(op.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInscription(@PathVariable Integer id) {
        Optional<Inscription> op = inscriptionService.findById(id);
        if (!op.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inscripción no existe");
        }
        inscriptionService.deleteById(id);
        return ResponseEntity.ok("Inscripción eliminada");
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancelInscription(@PathVariable Integer id) {
        Optional<Inscription> inscriptionOp = inscriptionService.findById(id);
        if (!inscriptionOp.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inscripción no encontrada");
        }

        Inscription inscripcion = inscriptionOp.get();

        // Verificar que la inscripción esté activa
        if (!"ACTIVE".equals(inscripcion.getStatus())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("La inscripción ya está cancelada");
        }

        // Cancelar inscripción y liberar espacio
        Inscription cancelledInscription = inscriptionService.cancelInscriptionAndFreeSpace(inscripcion);

        if (cancelledInscription == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cancelar la inscripción");
        }

        return ResponseEntity.ok("Inscripción cancelada");
    }

    @GetMapping("/evento/{eventId}")
    public List<Inscription> listByEvent(@PathVariable Integer eventId) {
        return inscriptionService.findByEventId(eventId);
    }

    @GetMapping("/evento/{eventId}/estado")
    public ResponseEntity<?> eventoStatus(@PathVariable Integer eventId) {
        Optional<Event> eventOp = eventService.findEventById(eventId);
        if (!eventOp.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento no encontrado");
        }

        Event event = eventOp.get();
        boolean active = LocalDate.parse(event.getDate()).isAfter(LocalDate.now());
        int inscriptions = inscriptionService.findActiveByEventId(eventId).size();

        Map<String, Object> data = new HashMap<>();
        data.put("eventoActivo", active);
        data.put("inscripcionesActivas", inscriptions);

        return ResponseEntity.ok(data);
    }

    @GetMapping("/user/{userId}")
    public List<Inscription> findByUserId(@PathVariable Integer userId) {
        return inscriptionService.findByUserId(userId);
    }
}//end class