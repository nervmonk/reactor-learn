package com.reactorintroduction.sec06;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec06.assignments.ExternalServiceClient;
import com.reactorintroduction.sec06.assignments.InventoryService;
import com.reactorintroduction.sec06.assignments.RevenueService;

public class Lec06Assignment {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        var inventoryService = new InventoryService();
        var revenueService = new RevenueService();

        client.orderStream().subscribe(inventoryService::consume);
        client.orderStream().subscribe(revenueService::consume);

        inventoryService.stream()
                .subscribe(Util.subscriber("inventory"));

        revenueService.stream()
                .subscribe(Util.subscriber("revenue"));

        Util.sleepSeconds(30);
    }
}
