package restaurante;

import java.time.LocalDateTime;

public class VoucherRedemption {
    private final String voucherId;
    private final String participantId;
    private final LocalDateTime date;
    private final double amount;
    public VoucherRedemption(String vid, String pid, LocalDateTime date, double amount) {
        this.voucherId = vid; this.participantId = pid; this.date = date; this.amount = amount;
    }
    public String getParticipantId() { return participantId; }
    public double getAmount() { return amount; }
}
