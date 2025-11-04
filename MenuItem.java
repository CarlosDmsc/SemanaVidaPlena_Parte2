package restaurante;

public class MenuItem {
    private final String id;
    private final String name;
    private final MenuCategory category;
    private final double price;
    public MenuItem(String id, String name, MenuCategory category, double price) {
        this.id = id; this.name = name; this.category = category; this.price = price;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public MenuCategory getCategory() { return category; }
    public double getPrice() { return price; }
}
