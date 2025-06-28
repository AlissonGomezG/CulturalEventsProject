package ucr.ac.cr.CulturalEvent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.CulturalEvent.model.Event;
import ucr.ac.cr.CulturalEvent.model.Inscription;
import ucr.ac.cr.CulturalEvent.repository.InscriptionRepository;



import java.util.List;
import java.util.Optional;

@Service
public class InscriptionService {

    @Autowired
    private EventService eventService;
    @Autowired
    private InscriptionRepository inscriptionRepository;

    public Inscription saveInscription(Inscription inscription) {
        Event event = inscription.getEvent();
        Integer currentSpaces = event.getAvailableSpace();
        event.setAvailableSpace(currentSpaces - 1);
        return inscriptionRepository.save(inscription);
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

    public Inscription cancelInscriptionAndFreeSpace(Inscription inscription) {
        // Cancelar la inscripción
        inscription.setStatus("CANCELLED");
        Inscription cancelledInscription = inscriptionRepository.save(inscription);

        // Liberar el espacio en el evento
        Event event = inscription.getEvent();
        Integer currentSpaces = event.getAvailableSpace();
        event.setAvailableSpace(currentSpaces + 1);

        // Guardar el evento con el espacio liberado
        eventService.saveEvent(event); // Necesitas tener este método en EventService

        return cancelledInscription;
    }

}//end class

