package com.restorant.util;




import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restorant.entity.MenuItem;
import com.restorant.entity.Order;
import com.restorant.entity.Waiter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String MENU_FILE = "menularustoz.json";
    private static final String WAITERS_FILE = "waiterslar.json";
    private static final String ORDERS_FILE = "lar.json";

    public static void saveMenuItems(List<MenuItem> menuItems) throws IOException {
        mapper.writeValue(new File(MENU_FILE), menuItems);
    }

    public static List<MenuItem> loadMenuItems() throws IOException {
        File file = new File(MENU_FILE);
        if (!file.exists()) return new ArrayList<>();
        return mapper.readValue(file, new TypeReference<List<MenuItem>>() {});
    }

    public static void saveWaiters(List<Waiter> waiters) throws IOException {
        mapper.writeValue(new File(WAITERS_FILE), waiters);
    }

    public static List<Waiter> loadWaiters() throws IOException {
        File file = new File(WAITERS_FILE);
        if (!file.exists()) return new ArrayList<>();
        return mapper.readValue(file, new TypeReference<List<Waiter>>() {});
    }

    public static void saveOrders(List<Order> orders) throws IOException {
        mapper.writeValue(new File(ORDERS_FILE), orders);
    }

    public static List<Order> loadOrders() throws IOException {
        File file = new File(ORDERS_FILE);
        if (!file.exists()) return new ArrayList<>();
        return mapper.readValue(file, new TypeReference<List<Order>>() {});
    }
}
