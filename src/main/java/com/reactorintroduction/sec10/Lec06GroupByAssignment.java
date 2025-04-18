package com.reactorintroduction.sec10;

import java.time.Duration;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec10.groupby.OrderProcessingService;
import com.reactorintroduction.sec10.groupby.PurchaseOrder;

import reactor.core.publisher.Flux;

public class Lec06GroupByAssignment {
    public static void main(String[] args) {
        orderStream()
                .filter(OrderProcessingService.canProcess())
                .groupBy(PurchaseOrder::category)
                .flatMap(gf -> gf.transform(OrderProcessingService.getProcessor(gf.key())))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static Flux<PurchaseOrder> orderStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> PurchaseOrder.create());
    }
}
