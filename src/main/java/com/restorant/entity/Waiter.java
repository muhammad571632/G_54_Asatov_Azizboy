package com.restorant.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Waiter {
    private String id;
    private String name;

    public Waiter(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public Object getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}