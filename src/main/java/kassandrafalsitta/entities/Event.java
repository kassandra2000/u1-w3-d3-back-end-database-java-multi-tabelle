package kassandrafalsitta.entities;

import jakarta.persistence.*;
import kassandrafalsitta.enums.EventType;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "events")
public class Event {
    //attributi
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    @Column(name = "event_date")
    private LocalDate eventDate;
    private String description;
    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    @Column(name = "num_max_participants")
    private int maximumNumberOfParticipants;

    //costruttore
    public Event() {
    }

    public Event(String title, LocalDate eventDate, String description, EventType eventType, int maximumNumberOfParticipants) {
        this.title = title;
        this.eventDate = eventDate;
        this.description = description;
        this.eventType = eventType;
        this.maximumNumberOfParticipants = maximumNumberOfParticipants;
    }

    //getter e setter

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public int getMaximumNumberOfParticipants() {
        return maximumNumberOfParticipants;
    }

    public void setMaximumNumberOfParticipants(int maximumNumberOfParticipants) {
        this.maximumNumberOfParticipants = maximumNumberOfParticipants;
    }

    //to string

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", title='" + title + '\'' + ", eventDate=" + eventDate + ", description='" + description + '\'' + ", eventType=" + eventType + ", maximumNumberOfParticipants='" + maximumNumberOfParticipants + '\'' + '}';
    }
}
