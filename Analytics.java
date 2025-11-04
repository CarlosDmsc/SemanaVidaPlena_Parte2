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
}
