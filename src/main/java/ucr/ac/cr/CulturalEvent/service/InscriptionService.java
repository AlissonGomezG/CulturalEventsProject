package ucr.ac.cr.CulturalEvent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.CulturalEvent.model.Event;
import ucr.ac.cr.CulturalEvent.model.Inscription;
import ucr.ac.cr.CulturalEvent.model.User;
import ucr.ac.cr.CulturalEvent.repository.InscriptionRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class InscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    public List<Inscription> findAllInscription() {
        return inscriptionRepository.findAll();
    }

    public Optional<Inscription> findInscriptionById(Integer id) {
        return inscriptionRepository.findById(id);
    }

    public Inscription saveInscription(Integer userId, Integer eventId) {
        Optional<User> userOp = userService.findUserById(userId);
        if (!userOp.isPresent()) {
            return null;
        }

        Optional<Event> eventOp = eventService.findEventById(eventId);
        if (!eventOp.isPresent()) {
            return null;
        }

        Event event = eventOp.get();

        Integer activeInscriptions = inscriptionRepository.countActiveInscriptionsByEventId(eventId);
        if (activeInscriptions >= event.getAvailableSpace()) {
            return null;
        }

        if (inscriptionRepository.existsByUserIdAndEventIdAndStatus(userId, eventId, "ACTIVE")) {
            return null;
        }

        Inscription inscription = new Inscription();
        inscription.setUser(userOp.get());
        inscription.setEvent(event);
        inscription.setInscriptionDate(getCurrentDateTime());
        inscription.setStatus("ACTIVE");

        return inscriptionRepository.save(inscription);
    }

    public void deleteInscription(Integer id) {
        inscriptionRepository.deleteById(id);
    }

    public Inscription editInscription(Integer id, Inscription inscriptionEdit) {
        Optional<Inscription> inscriptionOp = inscriptionRepository.findById(id);
        if (inscriptionOp.isPresent()) {
            Inscription inscription = inscriptionOp.get();
            inscription = inscriptionEdit;
            return inscriptionRepository.save(inscription);
        }
        return null;
    }

    public Inscription cancelInscription(Integer id) {
        Optional<Inscription> inscriptionOp = inscriptionRepository.findById(id);
        if (inscriptionOp.isPresent()) {
            Inscription inscription = inscriptionOp.get();
            inscription.setStatus("CANCELLED");
            return inscriptionRepository.save(inscription);
        }
        return null;
    }

    public List<Inscription> findInscriptionsByUserId(Integer userId) {
        return inscriptionRepository.findByUserId(userId);
    }

    public List<Inscription> findInscriptionsByEventId(Integer eventId) {
        return inscriptionRepository.findByEventId(eventId);
    }

    public List<Inscription> findActiveInscriptionsByUserId(Integer userId) {
        return inscriptionRepository.findByUserIdAndStatus(userId, "ACTIVE");
    }

    public List<Inscription> findActiveInscriptionsByEventId(Integer eventId) {
        return inscriptionRepository.findByEventIdAndStatus(eventId, "ACTIVE");
    }

    private String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
