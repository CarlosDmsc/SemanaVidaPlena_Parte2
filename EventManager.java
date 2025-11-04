package eventos;

import java.util.*;

public class EventManager {
    private final Map<String, Event> events = new HashMap<>();
    private final Map<String, Participant> participants = new HashMap<>();
    private final List<Voucher> vouchers = new ArrayList<>();

    public void addEvent(Event e) { events.put(e.getId(), e); }
    public void registerParticipant(Participant p) { participants.put(p.getId(), p); }
    public void markAttendance(String eventId, String participantId) { events.get(eventId).addParticipant(participantId); }

    public Voucher issueVoucherForEvent(String eventId, String code) {
        Voucher v = new Voucher(UUID.randomUUID().toString(), code, eventId);
        vouchers.add(v);
        return v;
    }

    public Collection<Event> allEvents() { return events.values(); }
    public Collection<Participant> allParticipants() { return participants.values(); }
    public List<Voucher> vouchersForEvent(String eventId) {
        return vouchers.stream().filter(v -> v.getEventId().equals(eventId)).toList();
    }
    public List<Voucher> allVouchers() { return vouchers; }
}
