package com.restorant.service;

import com.restorant.entity.Waiter;
import com.restorant.util.JsonUtil;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class WaiterService {
    private List<Waiter> waiters;

    public WaiterService() throws IOException {
        this.waiters = JsonUtil.loadWaiters();
    }

    public void addWaiter(String name) throws IOException {
        String id = UUID.randomUUID().toString();
        waiters.add(new Waiter(id, name));
        JsonUtil.saveWaiters(waiters);
    }

    public List<Waiter> getWaiters() {
        return waiters;
    }

    public Waiter findWaiterById(String id) {
        return waiters.stream()
                .filter(waiter -> waiter.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}