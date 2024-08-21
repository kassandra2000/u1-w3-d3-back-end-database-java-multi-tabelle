package kassandrafalsitta;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u1w3d2");

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        EntityManager em = emf.createEntityManager();
//        EventDAO ev = new EventDAO(em);
//        List<Event> events = new ArrayList<>();
//
//        for (int i = 0; i <= 1; i++) {
//            try {
//                System.out.println("vuoi creare un evento?");
//                System.out.println("1. Si\n2. No");
//                String choice = sc.nextLine();
//                switch (choice) {
//                    case "1":
//                        events = ev.createEvent();
//                        System.out.println(events);
//                        i++;
//
//                        break;
//                    case "2":
//                        System.out.println("Uscita dal programma...");
//
//                        i++;
//                        break;
//                    default:
//                        System.out.println("il valore non è valido");
//                        i = 0;
//                        break;
//                }
//            } catch (Exception e) {
//                System.out.println("non è stato possibile creare l'oggetto");
//            }
//        }
//        for (int i = 0; i <= 1; i++) {
//            try {
//                System.out.println("\nVuoi salvare i tuoi eventi?");
//                System.out.println("1. Si\n2. No");
//
//                String saveChoice = sc.nextLine();
//                switch (saveChoice) {
//                    case "1":
//                        ev.save(events);
//                        i++;
//                        break;
//                    case "2":
//                        System.out.println("Uscita dal programma...");
//                        i++;
//                        break;
//                    default:
//                        System.out.println("Il valore non è valido");
//                        i = 0;
//                        break;
//                }
//            } catch (Exception e) {
//                System.out.println("Non è stato possibile salvare l'oggetto");
//            }
//        }
//        for (int i = 0; i <= 1; i++) {
//            try {
//                System.out.println("\nVuoi cercare i tuoi eventi tramite id?");
//                System.out.println("1. Si\n2. No");
//
//                String saveChoice = sc.nextLine();
//                switch (saveChoice) {
//                    case "1":
//
//                        try {
//                            System.out.println("Quale evento vuoi cercare tramite id?");
//                            Long findId = Long.valueOf(sc.nextLine());
//                            ev.findById(findId);
//                            i++;
//                        } catch (NumberFormatException e) {
//                            System.out.println("Inserisci il formato corretto");
//                            i--;
//                        } catch (Exception e) {
//                            System.out.println(e.getMessage());
//                            i--;
//                        }
//
//                        break;
//                    case "2":
//                        System.out.println("Uscita dal programma...");
//                        i++;
//                        break;
//                    default:
//                        System.out.println("Il valore non è valido");
//                        i = 0;
//                        break;
//                }
//            } catch (Exception e) {
//                System.out.println("Non è stato possibile cercare l'oggetto");
//            }
//        }
//        for (int i = 0; i <= 1; i++) {
//            try {
//                System.out.println("\nVuoi eliminare i tuoi eventi tramite id?");
//                System.out.println("1. Si\n2. No");
//
//                String saveChoice = sc.nextLine();
//                switch (saveChoice) {
//                    case "1":
//
//                        try {
//                            System.out.println("Quale evento vuoi eliminare tramite id?");
//                            long findByIdAndDelete = Long.parseLong(sc.nextLine());
//                            ev.findByIdAndDelete(findByIdAndDelete);
//                            i++;
//                        } catch (NumberFormatException e) {
//                            System.out.println("Inserisci il formato corretto");
//                            i--;
//                        } catch (Exception e) {
//                            System.out.println(e.getMessage());
//                            i--;
//                        }
//
//                        break;
//                    case "2":
//                        System.out.println("Uscita dal programma...");
//                        i++;
//                        break;
//                    default:
//                        System.out.println("Il valore non è valido");
//                        i = 0;
//                        break;
//                }
//            } catch (Exception e) {
//                System.out.println("Non è stato possibile cercare l'oggetto");
//            }
//        }

        System.out.println("hello world");
    }
}
