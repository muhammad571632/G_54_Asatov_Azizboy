package com.restorant.entity;

import com.restorant.entity.MenuItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Order {
    private String id;
    private int tableNumber;
    private String waiterId;
    private List<MenuItem> items = new ArrayList<>();
    private boolean closed;
    private double totalAmount;

    public Order(String id, int tableNumber) {
        this.id = id;
        this.tableNumber = tableNumber;
    }

    public Object getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(String waiterId) {
        this.waiterId = waiterId;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}