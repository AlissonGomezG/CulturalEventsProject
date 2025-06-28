package ucr.ac.cr.CulturalEvent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.CulturalEvent.model.Event;
import ucr.ac.cr.CulturalEvent.model.Inscription;
import ucr.ac.cr.CulturalEvent.model.User;
import ucr.ac.cr.CulturalEvent.repository.InscriptionRepository;
import ucr.ac.cr.CulturalEvent.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class InscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    public Inscription saveInscription(Inscription inscription) {
        return inscriptionRepository.save(inscription);
    }

    public List<Inscription> findAllInscription() {
        return inscriptionRepository.findAll();
    }

    public Optional<Inscription> findInscriptionById(Integer id) {
        return inscriptionRepository.findById(id);
    }

    public void deleteInscription(Integer id) {
        inscriptionRepository.deleteById(id);
    }

    public List<Inscription> findByEventId(Integer eventId) {
        return inscriptionRepository.findByEventId(eventId);
    }


    public List<Inscription> findActiveByEventId(Integer id) {
        return inscriptionRepository.findByEventIdAndStatus(id, "ACTIVE");
    }

    public List<Inscription> findAll() {
        return inscriptionRepository.findAll();
    }

    public Optional<Inscription> findById(Integer id) {
        return inscriptionRepository.findById(id);
    }

    public void deleteById(Integer id) {
        inscriptionRepository.deleteById(id);
    }
}