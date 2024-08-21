package kassandrafalsitta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import kassandrafalsitta.dao.EventDAO;
import kassandrafalsitta.dao.LocationDAO;
import kassandrafalsitta.dao.PartecipationDAO;
import kassandrafalsitta.dao.PersonDAO;
import kassandrafalsitta.entities.Event;
import kassandrafalsitta.entities.Person;
import kassandrafalsitta.enums.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static kassandrafalsitta.entities.Event.createEvent;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u1w3d3");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EntityManager em = emf.createEntityManager();
        EventDAO ev = new EventDAO(em);
        LocationDAO ld = new LocationDAO(em);
        PartecipationDAO pd = new PartecipationDAO(em);
        PersonDAO prsd = new PersonDAO(em);
        List<Event> events = new ArrayList<>();

        Person person = new Person("gigo", "soretti", "gigo@gmail.com", LocalDate.parse("2000-12-11"), Gender.M);
        List<Person> persons = new ArrayList<>();
        persons.add(person);
//        prsd.save(persons);

        while (true) {
            try {
                System.out.println("vuoi creare un evento?");
                System.out.println("1. Crea evento \n2. Salva evento \n3. Cerca evento \n4. Elimina evento\n5. Esci");
                String choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        events = createEvent();
                        System.out.println(events);
                        break;
                    case "2":
                        ev.save(events);
                        break;
                    case "3":
                        try {
                            System.out.println("Quale evento vuoi cercare tramite id?");
                            UUID findId = UUID.fromString(sc.nextLine());
                            ev.findById(findId);

                        } catch (NumberFormatException e) {
                            System.out.println("Inserisci il formato corretto\n");

                        } catch (Exception e) {
                            System.out.println(e.getMessage());

                        }
                        break;
                    case "4":
                        try {
                            System.out.println("Quale evento vuoi eliminare tramite id?");
                            UUID findByIdAndDelete = UUID.fromString(sc.nextLine());
                            ev.findByIdAndDelete(findByIdAndDelete);

                        } catch (NumberFormatException e) {
                            System.out.println("Inserisci il formato corretto");

                        } catch (Exception e) {
                            System.out.println(e.getMessage());

                        }
                        break;
                    default:
                        System.out.println("il valore non è valido");
                        break;
                }
                if (choice.equals("5")) {
                    System.out.println("Uscita dal programma...");
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        em.close();
        emf.close();
    }


}
