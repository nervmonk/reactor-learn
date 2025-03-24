package com.reactorintroduction.sec03;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec03.assignment.StockPriceObserver;
import com.reactorintroduction.sec03.client.ExternalServiceClient;

public class Lec12Assignment {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        var subscriber = new StockPriceObserver();
        client.getPriceChanges()
        .subscribe(subscriber);

        Util.sleepSeconds(20);
    }
}
