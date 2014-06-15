package se.andreasottesen.yourmenu.app.restaurant;

/**
 * Created by Andreas on 2014-06-14.
 */
public class Item {
    public String id;
    public String name;
    public String description;
    public String restaurant;
    public float price;
    public Vendor vendor;

    public Item(String id, String name, String description, String restaurant, float price, Vendor vendor) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.restaurant = restaurant;
        this.price = price;
        this.vendor = vendor;
    }

    @Override
    public String toString() {
        return name;
    }
}
