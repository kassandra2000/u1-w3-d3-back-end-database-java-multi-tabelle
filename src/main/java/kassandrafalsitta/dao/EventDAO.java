package kassandrafalsitta.dao;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import kassandrafalsitta.entities.Event;
import kassandrafalsitta.enums.EventType;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Supplier;

public class EventDAO {
    //attributi
    private final EntityManager em;
    Faker fk = new Faker();
    Random r = new Random();//supplier
    Supplier<Event> eventSupplier = () -> {
        EventType[] eventTypeList = EventType.values();
        LocalDate date = fk.date().birthday().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return new Event(fk.esports().event(), date, fk.weather().description(), eventTypeList[r.nextInt(eventTypeList.length)], r.nextInt(1, 40));
    };
    Supplier<Event> eventSupplierWithScanner = () -> {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("inserisci titolo");
                String title = sc.nextLine();
                System.out.println("inserisci data");
                LocalDate date = LocalDate.parse(sc.nextLine());
                System.out.println("inserisci descrizione");
                String description = sc.nextLine();
                System.out.println("inserisci tipo: PUBBLICO o PRIVATO");
                EventType type = EventType.valueOf(sc.nextLine());
                System.out.println("inserisci numero massimo di partecipanti");
                int nMaxPartecipants = Integer.parseInt(sc.nextLine());


                ;
                return new Event(title, date, description, type, nMaxPartecipants);

            } catch (InputMismatchException e) {
                System.out.println("inserisci i valori correttamente");

            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    };


    //costruttore
    public EventDAO(EntityManager em) {
        this.em = em;
    }

    //metodi
    public List<Event> createEvent() {
        List<Event> eventList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int numOfEvents;
        while (true) {
            System.out.println("quanti eventi vuoi creare?");
            try {
                numOfEvents = Integer.parseInt(sc.nextLine());
                break;
            } catch (InputMismatchException e) {
                System.out.println("inserisci un numero valido");

            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }

        }

        System.out.println("1. creali tu \n 2. creali random");
        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    for (int i = 0; i < numOfEvents; i++) {
                        eventList.add(eventSupplierWithScanner.get());
                    }
                    break;
                case 2:
                    for (int i = 0; i < numOfEvents; i++) {
                        eventList.add(eventSupplier.get());
                    }
                    System.out.println("eventi creati con successo");
                    break;
                default:
                    System.out.println("scelta non valida riprova!");
                    break;
            }

        } catch (InputMismatchException e) {
            System.out.println("inserisci un numero valido");

        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
        ;
        return eventList;
    }

    public void save(List<Event> eventList) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        for (Event event : eventList) {
            em.persist(event);
        }
        transaction.commit();
        System.out.println("gli eventi sono stati aggiunti con successo");
    }

    public Event findById(Long eventId) {
        Event eventFound = null;
        try {
            eventFound = em.find(Event.class, eventId);

            if (eventFound == null) System.out.println("L'evento con id: " + eventId + " non è stato trovato");
            else {
                System.out.println("\necco l'evento che hai cercato:");
                System.out.println(eventFound);
            }

        } catch (Exception e) {
            System.out.println("Non è stato possibile rimuovere l'evento con id: " + eventId);
        }
        return eventFound;
    }

    public void findByIdAndDelete(long eventId) {
        try {
            Event eventFound = this.findById(eventId);
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(eventFound);
            transaction.commit();
            System.out.println("L'evento con id: " + eventFound.getTitle() + "  è stato rimosso con successo");

        } catch (IllegalArgumentException e) {
            System.out.println("l'elemento da eliminare con id: " + eventId + " non esiste");
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
}
