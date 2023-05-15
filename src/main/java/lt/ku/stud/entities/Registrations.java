package lt.ku.stud.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "registrations")

public class Registrations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20)
    private String registration_date;

    @ManyToOne
    @JoinColumn(name = "Clients_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "Workouts_id")
    private Workouts workouts;

    public Registrations() {
    }

    public Registrations(String registration_date, Workouts workouts, Client client) {
        this.registration_date = registration_date;
        this.workouts = workouts;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Workouts getWorkouts() {
        return workouts;
    }

    public void setWorkouts(Workouts workouts) {
        this.workouts = workouts;
    }
}
