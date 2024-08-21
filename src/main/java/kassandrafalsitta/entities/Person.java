package kassandrafalsitta.entities;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import kassandrafalsitta.enums.EventType;
import kassandrafalsitta.enums.Gender;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Entity
@Table(name = "person")
public class Person {
    static Faker fk = new Faker();
    static Random r = new Random();
    //attributi
    @Id
    @GeneratedValue
    @Column(name = "person_id")
    private UUID personId;
    private String name;
    private String surname;
    private String email;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirt;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "personId")
    private List<Participation> partecipationList;
    //costruttore

    public Person() {
    }

    public Person(String name, String surname, String email, LocalDate dateOfBirt, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirt = dateOfBirt;
        this.gender = gender;
    }

    //metodi
    public static Person createPartecipation() {
        Gender[] genderList = Gender.values();
        LocalDate date = fk.date().birthday().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return new Person(fk.name().firstName(), fk.name().lastName(), fk.internet().emailAddress(), date, genderList[r.nextInt(genderList.length)]);
    }

    //metodi
    public static Person CreatePersonWithScanner() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("inserisci nome");
                String name = sc.nextLine();
                System.out.println("inserisci cognome");
                String surname = sc.nextLine();
                System.out.println("inserisci data");
                LocalDate date = LocalDate.parse(sc.nextLine());
                System.out.println("inserisci descrizione");
                String description = sc.nextLine();
                System.out.println("inserisci tipo: PUBBLICO o PRIVATO");
                EventType type = EventType.valueOf(sc.nextLine());
                System.out.println("inserisci numero massimo di partecipanti");
                int nMaxPartecipants = Integer.parseInt(sc.nextLine());

                return new Event(name, date, description, type, nMaxPartecipants);

            } catch (InputMismatchException e) {
                System.out.println("inserisci i valori correttamente");

            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }
    //getter e setter

    public UUID getPersonId() {
        return personId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirt() {
        return dateOfBirt;
    }

    public void setDateOfBirt(LocalDate dateOfBirt) {
        this.dateOfBirt = dateOfBirt;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Participation> getPartecipationList() {
        return partecipationList;
    }

    public void setPartecipationList(List<Participation> partecipationList) {
        this.partecipationList = partecipationList;
    }


    //to string


    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirt=" + dateOfBirt +
                ", gender=" + gender +
                // ", partecipationList=" + partecipationList +
                '}';
    }
}
