package com.reactorintroduction.sec06.assignments;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import reactor.core.publisher.Flux;

public class InventoryService implements OrderProcessor {

    private final Map<String, Integer> db = new HashMap<>();

    @Override
    public void consume(Order order) {
        var currentInventory = db.getOrDefault(order.category(), 500);
        var updatedInventory = currentInventory - order.quantity();
        db.put(order.category(), updatedInventory);
    }

    @Override
    public Flux<String> stream() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> this.db.toString());
    }

}
