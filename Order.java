package restaurante;

import java.time.LocalDateTime;
import java.util.*;

public class Order {
    private final String id;
    private final String participantId;
    private final String restaurantId;
    private final LocalDateTime date;
    private final List<MenuItem> items;
    public Order(String id, String pid, String rid, LocalDateTime d, List<MenuItem> i) {
        this.id = id; this.participantId = pid; this.restaurantId = rid; this.date = d; this.items = new ArrayList<>(i);
    }
    
    public String getParticipantId() { return participantId; }
    public String getRestaurantId() { return restaurantId; }
    public LocalDateTime getDate() { return date; }
    public List<MenuItem> getItems() { return items; }
}
