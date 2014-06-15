package se.andreasottesen.yourmenu.app.restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andreas on 2014-05-30.
 */
public class ItemContent {
    public static List<Item> ITEMS = new ArrayList<Item>();
    public static List<Vendor> VENDORS = new ArrayList<Vendor>();

    public static Map<String, Item> ITEM_MAP = new HashMap<String, Item>();
    public static Map<String, Vendor> VENDOR_MAP = new HashMap<String, Vendor>();

    static {
        Vendor vendor = new Vendor("1", "Gamla stans kafé", "Det äldsta kaféet någonsin.", 59.325695, 18.071869);
        VENDORS.add(vendor);
        VENDOR_MAP.put(vendor.id, vendor);

        // Add sample items.
        addItem(new Item("1", "Item 1", "description 1", "restaurant 1", 10, vendor));
        addItem(new Item("2", "Item 2", "description 2", "restaurant 2", 20, vendor));
        addItem(new Item("3", "Item 3", "description 3", "restaurant 3", 30, vendor));
        addItem(new Item("4", "Item 4", "description 4", "restaurant 4", 40, vendor));
        addItem(new Item("5", "Item 5", "description 5", "restaurant 5", 50, vendor));
        addItem(new Item("6", "Item 6", "description 6", "restaurant 6", 60, vendor));
    }

    private static void addItem(Item item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
}
