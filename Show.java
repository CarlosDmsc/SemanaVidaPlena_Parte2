package eventos;
import java.time.LocalDateTime;
public class Show extends Event {
    public Show(String id, String title, LocalDateTime date) { super(id, title, date); }
    public EventType getType() { return EventType.SHOW; }
}
