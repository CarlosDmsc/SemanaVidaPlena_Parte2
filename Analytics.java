package analytics;

import eventos.*;
import restaurante.*;
import java.util.*;
import java.util.stream.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class Analytics {
    public static void runReports(EventManager em, RestaurantManager rm) {
        System.out.println("\n========= RELATÓRIOS =========");
        System.out.println("1) Quantos participantes usaram desconto: " + countVoucherUsersAfterEvent(em, rm));
        System.out.println("2) Restaurante mais visitado: " + mostVisitedRestaurant(em, rm));
        System.out.println("3) Tempo médio (horas): " + averageDelay(em, rm));
        System.out.println("4) Evento com mais visitas: " + eventWithMostVisits(em, rm));
        System.out.println("5) Consumidores de combos com vários eventos: " + comboConsumers(em, rm));
        System.out.printIn("6) Qual é o prato mais pedido entre os participantes de determinado evento?" + mostOrderedItemForEvent(em, rm, eventId));
        System.out.printIn("7) Qual restaurante parceiro teve o maior faturamento proveniente de vouchers distribuídos em eventos?" + restaurantWithHighestVoucherRevenue(rm));
        System.out.printIn("8) Existe correlação entre tipo de evento e tipo de refeição mais consumida?" + correlationEventTypeToMealCategory(em, rm));
        System.out.printIn("Quais eventos tiveram a maior taxa de conversão de participantes em clientes de restaurante parceiro?" + topEventsByConversion(em, rm));
    }

    public static long countVoucherUsersAfterEvent(EventManager em, RestaurantManager rm) {
        return rm.allVoucherRedemptions().stream().map(VoucherRedemption::getParticipantId).distinct().count();
    }

    public static String mostVisitedRestaurant(EventManager em, RestaurantManager rm) {
        return rm.allOrders().stream()
            .collect(Collectors.groupingBy(Order::getRestaurantId, Collectors.counting()))
            .entrySet().stream().max(Map.Entry.comparingByValue())
            .map(e -> e.getKey() + " (" + e.getValue() + ")").orElse("Nenhum");
    }

    public static double averageDelay(EventManager em, RestaurantManager rm) {
        List<Long> diffs = new ArrayList<>();
        for (Order o : rm.allOrders()) {
            em.allEvents().stream().filter(e -> e.getParticipantIds().contains(o.getParticipantId())).findFirst()
                .ifPresent(e -> diffs.add(ChronoUnit.HOURS.between(e.getDate(), o.getDate())));
        }
        return diffs.stream().mapToLong(Long::longValue).average().orElse(0.0);
    }

    public static String eventWithMostVisits(EventManager em, RestaurantManager rm) {
        Map<String, Long> map = new HashMap<>();
        for (Event e : em.allEvents()) {
            long count = rm.allOrders().stream().filter(o -> e.getParticipantIds().contains(o.getParticipantId())).count();
            map.put(e.getId(), count);
        }
        return map.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("Nenhum");
    }

    public static Set<String> comboConsumers(EventManager em, RestaurantManager rm) {
        return rm.allOrders().stream()
            .filter(o -> o.getItems().stream().anyMatch(i -> i.getCategory() == MenuCategory.COMBO))
            .map(Order::getParticipantId).collect(Collectors.toSet());
    }

    public static String mostOrderedItemForEvent(EventManager em, RestaurantManager rm, String eventId) {
        return em.allEvents().stream()
            .filter(e -> e.getId().equals(eventId))
            .findFirst()
            .map(e -> {
                Set<String> participants = e.getParticipantIds();
                return rm.allOrders().stream()
                    .filter(o -> participants.contains(o.getParticipantId()))
                    .flatMap(o -> o.getItems().stream())
                    .collect(Collectors.groupingBy(MenuItem::getName, Collectors.counting()))
                    .entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(x -> x.getKey() + " (" + x.getValue() + " pedidos)")
                    .orElse("Nenhum pedido encontrado");
            })
            .orElse("Evento não encontrado");
    }

    public static String restaurantWithHighestVoucherRevenue(RestaurantManager rm) {
        return rm.allVoucherRedemptions().stream()
            .collect(Collectors.groupingBy(VoucherRedemption::getParticipantId, Collectors.summingDouble(VoucherRedemption::getAmount)))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(e -> "Restaurante ID " + e.getKey() + " - R$ " + String.format("%.2f", e.getValue()))
            .orElse("Nenhum voucher utilizado");
    }

    public static Map<EventType, Map<MenuCategory, Long>> correlationEventTypeToMealCategory(EventManager em, RestaurantManager rm) {
        Map<EventType, Map<MenuCategory, Long>> correlation = new HashMap<>();
        for (Event e : em.allEvents()) {
            Map<MenuCategory, Long> counts = new HashMap<>();
            for (Order o : rm.allOrders()) {
                if (e.getParticipantIds().contains(o.getParticipantId())) {
                    for (MenuItem item : o.getItems()) {
                        counts.put(item.getCategory(), counts.getOrDefault(item.getCategory(), 0L) + 1);
                    }
                }
            }
            correlation.put(e.getType(), counts);
        }
        return correlation;
    }

    public static Map<String, Double> topEventsByConversion(EventManager em, RestaurantManager rm) {
        Map<String, Double> conversionRates = new HashMap<>();
        for (Event e : em.allEvents()) {
            Set<String> participants = e.getParticipantIds();
            if (participants.isEmpty()) continue;
            long converted = rm.allOrders().stream()
                .filter(o -> participants.contains(o.getParticipantId()))
                .map(Order::getParticipantId)
                .distinct()
                .count();
            double rate = (double) converted / participants.size();
            conversionRates.put(e.getTitle(), rate);
        }

        return conversionRates.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                    (a, b) -> a, LinkedHashMap::new));
    }
}

