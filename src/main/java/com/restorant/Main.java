package com.restorant;

import com.restorant.entity.Order;
import com.restorant.entity.Waiter;
import com.restorant.service.MenuService;
import com.restorant.service.OrderService;
import com.restorant.service.WaiterService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            MenuService menuService = new MenuService();
            WaiterService waiterService = new WaiterService();
            OrderService orderService = new OrderService(menuService);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("""
                        =====================
                        1. Tavom qo'shish
                        2. Girgitton qoshish            
                        3. Zakaz  yaratish                       
                        4. Zakaz ga ovqat qoshish                                          
                        5. Zakaz yopish                       
                        6. Girgiton yoki Girgitonxon statiskalari                        
                        7. buyurtmalar                     
                        8  exit  
                        
                        
                        tanlov ? >>> \s
                        """);

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Tavom nomini kiriting: ");
                        String itemName = scanner.nextLine();
                        System.out.print("Tavom narxini kiriting: ");
                        double price = scanner.nextDouble();
                        menuService.addMenuItem(itemName, price);
                        System.out.println("qoshildi");
                        break;
                    case 2:
                        System.out.print("Girgitton: ");
                        String waiterName = scanner.nextLine();
                        waiterService.addWaiter(waiterName);
                        System.out.println("Girgitton qoshildi !");
                        break;
                    case 3:
                        System.out.print("Stol raqamini kiriting: ");
                        int tableNumber = scanner.nextInt();
                        orderService.createOrder(tableNumber);
                        System.out.println("Buyurtma yaratilddi !");
                        break;
                    case 4:
                        System.out.print("Zakaz idisini kiriting : ");
                        String orderId = scanner.nextLine();
                        System.out.print("Ovqat nomini kiriting : ");
                        String menuItemName = scanner.nextLine();
                        orderService.addItemToOrder(orderId, menuItemName);
                        System.out.println("Qoshildi ");
                        break;
                    case 5:
                        System.out.print("Buyurtma qoshildi !!!!  ");
                        String closeOrderId = scanner.nextLine();
                        System.out.print("Girgitton bek - xon  id  sini kiriting: ");
                        String waiterId = scanner.nextLine();
                        orderService.closeOrder(closeOrderId, waiterId);
                        System.out.println("Zakaz yopildi !");
                        break;
                    case 6:
                        Map<String, Map<String, Object>> stats = orderService.getWaiterStatistics();
                        for (Map.Entry<String, Map<String, Object>> entry : stats.entrySet()) {
                            String wId = entry.getKey();
                            Waiter waiter = waiterService.findWaiterById(wId);
                            System.out.println("Girgitton : " + (waiter != null ? waiter.getName() : wId));
                            System.out.println("Yopilgan zakaz soni: " + entry.getValue().get("irder lar soni "));
                            System.out.println("Umumiy summa: " + entry.getValue().get("totalar "));
                        }
                        break;
                    case 7:
                        System.out.print("Stol raqamini kiriting: ");
                        int tableNum = scanner.nextInt();
                        List<Order> tableOrders = orderService.getOrdersByTable(tableNum);
                        for (Order order : tableOrders) {
                            System.out.println("Buyurtma id: "
                                    + order.getId() +
                                    ", Stol: " +
                                    order
                                            .getTableNumber() +
                                    ", Umumiy summa: " +
                                    order.getTotalAmount() +
                                    ", Yopilgan: " + order.isClosed());
                        }
                        break;
                    case 8:
                        System.out.println("Xayr salomat bolin ....!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invallid command !");
                }
            }
        } catch (IOException e) {
            System.out.println("Faylda xatolik  " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Xatolik : " + e.getMessage());
        }
    }
}
