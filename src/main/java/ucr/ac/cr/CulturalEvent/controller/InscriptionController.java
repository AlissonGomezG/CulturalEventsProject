package ucr.ac.cr.CulturalEvent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.CulturalEvent.model.Inscription;
import ucr.ac.cr.CulturalEvent.model.DTO.InscriptionDTO;
import ucr.ac.cr.CulturalEvent.service.InscriptionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/inscriptions")
public class InscriptionController {

    @Autowired
    InscriptionService inscriptionService;

    @GetMapping
    public List<Inscription> findAllInscriptions() {
        return inscriptionService.findAllInscription();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInscription(@PathVariable Integer id) {
        Optional<Inscription> inscription = inscriptionService.findInscriptionById(id);
        if (!inscription.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La inscripción " + id + " no se encuentra");
        }
        return ResponseEntity.ok(inscription);
    }

    @PostMapping
    public ResponseEntity<?> saveInscription(@Validated @RequestBody InscriptionDTO inscriptionDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            Inscription savedInscription = inscriptionService.saveInscription(
                    inscriptionDTO.getUserId(),
                    inscriptionDTO.getEventId()
            );
            if (savedInscription == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("No se pudo crear la inscripción. Verifique que el usuario y evento existan, que haya espacios disponibles y que no esté ya inscrito.");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(savedInscription);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInscription(@PathVariable Integer id) {
        Optional<Inscription> inscriptionOp = inscriptionService.findInscriptionById(id);
        if (!inscriptionOp.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El ID " + id + " No se encuentra registrado");
        }
        inscriptionService.deleteInscription(id);
        return ResponseEntity.ok("Se eliminó la inscripción con el id: " + id);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancelInscription(@PathVariable Integer id) {
        Inscription cancelledInscription = inscriptionService.cancelInscription(id);
        if (cancelledInscription == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("La inscripción " + id + " no se encuentra");
        }
        return ResponseEntity.ok(cancelledInscription);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getInscriptionsByUser(@PathVariable Integer userId) {
        List<Inscription> inscriptions = inscriptionService.findInscriptionsByUserId(userId);
        return ResponseEntity.ok(inscriptions);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<?> getInscriptionsByEvent(@PathVariable Integer eventId) {
        List<Inscription> inscriptions = inscriptionService.findInscriptionsByEventId(eventId);
        return ResponseEntity.ok(inscriptions);
    }

    @GetMapping("/user/{userId}/active")
    public ResponseEntity<?> getActiveInscriptionsByUser(@PathVariable Integer userId) {
        List<Inscription> inscriptions = inscriptionService.findActiveInscriptionsByUserId(userId);
        return ResponseEntity.ok(inscriptions);
    }

    @GetMapping("/event/{eventId}/active")
    public ResponseEntity<?> getActiveInscriptionsByEvent(@PathVariable Integer eventId) {
        List<Inscription> inscriptions = inscriptionService.findActiveInscriptionsByEventId(eventId);
        return ResponseEntity.ok(inscriptions);
    }
}
