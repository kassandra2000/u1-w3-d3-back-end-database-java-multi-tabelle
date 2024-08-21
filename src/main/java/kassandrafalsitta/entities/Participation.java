package kassandrafalsitta.entities;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import kassandrafalsitta.dao.EventDAO;
import kassandrafalsitta.dao.PersonDAO;
import kassandrafalsitta.enums.State;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

@Entity
@Table(name = "partecipations")
public class Participation {
    static Faker fk = new Faker();
    static Random r = new Random();

    //attributi
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person personId;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event eventId;
    @Enumerated(EnumType.STRING)
    private State state;
    //costruttore

    public Participation() {
    }

    public Participation(Person personId, Event eventId, State state) {
        this.personId = personId;
        this.eventId = eventId;
        this.state = state;
    }

    public static Participation createPartecipation(EventDAO ev, PersonDAO pd, List<Event> evList, List<Person> psList) {
        State[] stateList = State.values();

//        evList[r.nextInt(evList.length)].
        return new Participation(pd.findById(psList.get(r.nextInt(psList.size())).getPersonId()), ev.findById(evList.get(r.nextInt(evList.size())).getId()), stateList[r.nextInt(stateList.length)]);
    }

    //metodi
    public static Participation CreateartecipationWithScanner(EventDAO ev, PersonDAO pd) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Inserisci l'ID della persona:");
                UUID personId = UUID.fromString(sc.nextLine());

                System.out.println("Inserisci l'ID dell'evento:");
                UUID eventId = UUID.fromString(sc.nextLine());

                System.out.println("Inserisci lo stato: CONFERMATO, DA_CONFERMARE;");
                State state = State.valueOf(sc.nextLine().toUpperCase());

                return new Participation(pd.findById(personId), ev.findById(eventId), state);

            } catch (IllegalArgumentException e) {
                System.out.println("Errore di formato: UUID o stato non valido. Riprova.");
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }
    //getter e setter

    public UUID getId() {
        return id;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    public Event getEventId() {
        return eventId;
    }

    public void setEventId(Event eventId) {
        this.eventId = eventId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }


    //to string

    @Override
    public String toString() {
        return "Participation{" +
                "id=" + id +
                ", personId=" + personId +
                ", eventId=" + eventId +
                ", state=" + state +
                '}';
    }
}
