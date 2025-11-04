package eventos;

import java.time.LocalDateTime;
import java.util.*;

public abstract class Event {
    private final String id;
    private final String title;
    private final LocalDateTime date;
    private final Set<String> participantIds = new HashSet<>();

    public Event(String id, String title, LocalDateTime date) {
        this.id = id; this.title = title; this.date = date;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public LocalDateTime getDate() { return date; }

    public void addParticipant(String pid) { participantIds.add(pid); }
    public Set<String> getParticipantIds() { return Collections.unmodifiableSet(participantIds); }

    public abstract EventType getType();
}