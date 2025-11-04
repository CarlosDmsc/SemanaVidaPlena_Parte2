package eventos;

import java.time.LocalDateTime;

public class Lecture extends Event {
    public Lecture(String id, String title, LocalDateTime date) { super(id, title, date); }
    public EventType getType() { return EventType.LECTURE; }
}
