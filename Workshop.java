package eventos;

import java.time.LocalDateTime;

public class Workshop extends Event {
    public Workshop(String id, String title, LocalDateTime date) { super(id, title, date); }
    public EventType getType() { return EventType.WORKSHOP; }
}
