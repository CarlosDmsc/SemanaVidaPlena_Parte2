package eventos;

import java.time.LocalDateTime;

public class Voucher {
    private final String id;
    private final String code;
    private final String eventId;
    private boolean used = false;
    @SuppressWarnings("unused")
    private LocalDateTime usedAt;
    private String redeemedBy;
    private String restaurantId;
    private double redeemedAmount = 0.0;

    public Voucher(String id, String code, String eventId) {
        this.id = id; this.code = code; this.eventId = eventId;
    }

    public String getId() { return id; }
    public String getCode() { return code; }
    public String getEventId() { return eventId; }
    public boolean isUsed() { return used; }

    public void redeem(String participantId, String restaurantId, LocalDateTime at, double amount) {
        this.used = true;
        this.usedAt = at;
        this.redeemedBy = participantId;
        this.restaurantId = restaurantId;
        this.redeemedAmount = amount;
    }

    public String getRedeemedBy() { return redeemedBy; }
    public String getRestaurantId() { return restaurantId; }
    public double getRedeemedAmount() { return redeemedAmount; }
}
