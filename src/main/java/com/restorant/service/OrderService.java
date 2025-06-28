package com.restorant.service;

import com.restorant.entity.MenuItem;
import com.restorant.entity.Order;
import com.restorant.util.JsonUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderService {
    private List<Order> orders;
    private MenuService menuService;

    public OrderService(MenuService menuService) throws IOException {
        this.menuService = menuService;
        this.orders = JsonUtil.loadOrders();
    }

    public void createOrder(int tableNumber) throws IOException {
        String id = UUID.randomUUID().toString();
        orders.add(new Order(id, tableNumber));
        JsonUtil.saveOrders(orders);
    }

    public void addItemToOrder(String orderId, String menuItemName) throws IOException {
        Order order = findOrderById(orderId);
        if (order == null || order.isClosed()) {
            throw new IllegalStateException("Zakaz topilmadi yoki mavjud xam emas!");
        }
        MenuItem item = menuService.getMenuItems().stream()
                .filter(menuItem -> menuItem.getName().equals(menuItemName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tavom menyuda topilmadi!"));
        order.getItems().add(item);
        order.setTotalAmount(order.getTotalAmount() + item.getPrice());
        JsonUtil.saveOrders(orders);
    }

    public void closeOrder(String orderId, String waiterId) throws IOException {
        Order order = findOrderById(orderId);
        if (order == null || order.isClosed()) {
            throw new IllegalStateException("Zakaz topilmadi yoki allaqachon yopilgan! :))))");
        }
        order.setWaiterId(waiterId);
        order.setClosed(true);
        JsonUtil.saveOrders(orders);
    }

    public Map<String, Map<String, Object>> getWaiterStatistics() {
        Map<String, Map<String, Object>> stats = new HashMap<>();
        orders.stream()
                .filter(Order::isClosed)
                .collect(Collectors.groupingBy(Order::getWaiterId, Collectors.toList()))
                .forEach((waiterId, waiterOrders) -> {
                    Map<String, Object> waiterStats = new HashMap<>();
                    waiterStats.put("ZakazlarSonni ", waiterOrders.size());
                    double totalSum = waiterOrders.stream()
                            .mapToDouble(Order::getTotalAmount)
                            .sum();
                    waiterStats.put("total ", totalSum);
                    stats.put(waiterId, waiterStats);
                });
        return stats;
    }

    public List<Order> getOrdersByTable(int tableNumber) {
        return orders.stream()
                .filter(order -> order.getTableNumber() == tableNumber)
                .collect(Collectors.toList());
    }

    private Order findOrderById(String id) {
        return orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
