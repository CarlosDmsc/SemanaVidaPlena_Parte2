package restaurante;

import java.time.LocalDateTime;
import java.util.*;

public class RestaurantManager {
    private final Map<String, Restaurant> restaurants = new HashMap<>();
    private final List<Order> allOrders = new ArrayList<>();
    private final List<VoucherRedemption> redemptions = new ArrayList<>();

    public void addRestaurant(Restaurant r) { restaurants.put(r.getId(), r); }
    public Collection<Restaurant> allRestaurants() { return restaurants.values(); }
    public void recordOrder(Order o) { allOrders.add(o); restaurants.get(o.getRestaurantId()).addOrder(o); }
    public void redeemVoucher(String voucherId, String pid, String rid, LocalDateTime at, double amount) {
        VoucherRedemption v = new VoucherRedemption(voucherId, pid, at, amount);
        redemptions.add(v);
        restaurants.get(rid).addRedemption(v);
    }
    public List<Order> allOrders() { return allOrders; }
    public List<VoucherRedemption> allVoucherRedemptions() { return redemptions; }
}
