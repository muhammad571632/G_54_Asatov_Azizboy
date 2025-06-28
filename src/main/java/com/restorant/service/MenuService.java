package com.restorant.service;

import com.restorant.entity.MenuItem;
import com.restorant.util.JsonUtil;

import java.io.IOException;
import java.util.List;

public class MenuService {
    private List<MenuItem> menuItems;

    public MenuService() throws IOException {
        this.menuItems = JsonUtil.loadMenuItems();
    }

    public void addMenuItem(String name, double price) throws IOException {
        menuItems.add(new MenuItem(name, price));
        JsonUtil.saveMenuItems(menuItems);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
}