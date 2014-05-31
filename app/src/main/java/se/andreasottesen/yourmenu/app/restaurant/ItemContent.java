package se.andreasottesen.yourmenu.app.restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andreas on 2014-05-30.
 */
public class ItemContent {
    /**
     * An array of sample (dummy) items.
     */
    public static List<Item> ITEMS = new ArrayList<Item>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, Item> ITEM_MAP = new HashMap<String, Item>();

    static {
        // Add 3 sample items.
        addItem(new Item("1", "Item 1", "description 1", "restaurant 1", 10));
        addItem(new Item("2", "Item 2", "description 2", "restaurant 2", 20));
        addItem(new Item("3", "Item 3", "description 3", "restaurant 3", 30));
        addItem(new Item("4", "Item 4", "description 4", "restaurant 4", 40));
        addItem(new Item("5", "Item 5", "description 5", "restaurant 5", 50));
        addItem(new Item("6", "Item 6", "description 6", "restaurant 6", 60));
    }

    private static void addItem(Item item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Item {
        public String id;
        public String name;
        public String description;
        public String restaurant;
        public float price;

        public Item(String id, String name, String description, String restaurant, float price) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.restaurant = restaurant;
            this.price = price;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
