package ucr.ac.cr.CulturalEvent.model;


import jakarta.persistence.*;

@Entity
@Table(name = "tb_inscription")
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 10)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "inscription_date", nullable = false, length = 50)
    private String inscriptionDate;

    @Column(name = "status", nullable = false, length = 20)
    private String status; // ACTIVE, CANCELLED

    public Inscription() {
    }

    public Inscription(Integer id, User user, Event event, String inscriptionDate, String status) {
        this.id = id;
        this.user = user;
        this.event = event;
        this.inscriptionDate = inscriptionDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getInscriptionDate() {
        return inscriptionDate;
    }

    public void setInscriptionDate(String inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
