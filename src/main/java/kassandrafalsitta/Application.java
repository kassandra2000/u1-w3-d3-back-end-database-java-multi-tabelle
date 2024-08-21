package kassandrafalsitta;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import kassandrafalsitta.dao.EventDAO;
import kassandrafalsitta.entities.Event;
import kassandrafalsitta.enums.EventType;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Supplier;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u1w3d3");
    static Faker fk = new Faker();
    static Random r = new Random();
    //supplier
    static Supplier<Event> eventSupplier = () -> {
        EventType[] eventTypeList = EventType.values();
        LocalDate date = fk.date().birthday().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return new Event(fk.esports().event(), date, fk.weather().description(), eventTypeList[r.nextInt(eventTypeList.length)], r.nextInt(1, 40));
    };
    static Supplier<Event> eventSupplierWithScanner = () -> {
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EntityManager em = emf.createEntityManager();
        EventDAO ev = new EventDAO(em);
        List<Event> events = new ArrayList<>();

        for (int i = 0; i <= 1; i++) {
            try {
                System.out.println("vuoi creare un evento?");
                System.out.println("1. Crea evento \n2. Salva evento \n3. Cerca evento \n4. Elimina evento\n5. Esci");
                String choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        events = createEvent();
                        System.out.println(events);
                        i--;

                        break;
                    case "2":
                        ev.save(events);
                        i--;
                        break;

                    case "3":
                        try {
                            System.out.println("Quale evento vuoi cercare tramite id?");
                            Long findId = Long.valueOf(sc.nextLine());
                            ev.findById(findId);
                            i++;
                        } catch (NumberFormatException e) {
                            System.out.println("Inserisci il formato corretto");
                            i--;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            i--;
                        }

                        break;
                    case "4":
                        try {
                            System.out.println("Quale evento vuoi eliminare tramite id?");
                            long findByIdAndDelete = Long.parseLong(sc.nextLine());
                            ev.findByIdAndDelete(findByIdAndDelete);
                            i++;
                        } catch (NumberFormatException e) {
                            System.out.println("Inserisci il formato corretto");
                            i--;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            i--;
                        }
                        i--;
                        break;

                    case "5":
                        System.out.println("Uscita dal programma...");

                        i++;
                        break;
                    default:
                        System.out.println("il valore non è valido");
                        i = 0;
                        break;
                }
            } catch (Exception e) {
                System.out.println("non è stato possibile creare l'oggetto");
            }
        }

    }

    public static List<Event> createEvent() {
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
}
