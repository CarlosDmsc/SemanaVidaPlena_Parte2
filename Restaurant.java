package restaurante;

import java.util.*;

public class Restaurant {
    private final String id;
    private final String name;
    private final List<MenuItem> menu = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private final List<VoucherRedemption> redemptions = new ArrayList<>();
    public Restaurant(String id, String name) { this.id = id; this.name = name; }
    public String getId() { return id; }
    public String getName() { return name; }
    public void addMenuItem(MenuItem m) { menu.add(m); }
    public void addOrder(Order o) { orders.add(o); }
    public void addRedemption(VoucherRedemption v) { redemptions.add(v); }
    public List<Order> getOrders() { return orders; }
    public List<VoucherRedemption> getRedemptions() { return redemptions; }
}
