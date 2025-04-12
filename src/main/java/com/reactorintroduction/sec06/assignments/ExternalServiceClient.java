package com.reactorintroduction.sec06.assignments;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactorintroduction.common.AbstractHttpClient;

import reactor.core.publisher.Flux;

public class ExternalServiceClient extends AbstractHttpClient {
    private static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);
    private Flux<Order> orderFlux;

    public Flux<Order> orderStream() {
        if (Objects.isNull(orderFlux)) {
            this.orderFlux = getOrderStream();
        }
        return this.orderFlux;
    }

    private Flux<Order> getOrderStream() {
        return this.httpClient.get()
                .uri("/demo04/orders/stream")
                .responseContent()
                .asString()
                .map(this::parse)
                .doOnNext(o -> log.info("{}", o))
                .publish()
                .refCount(2);
    }

    private Order parse(String message) {
        var arr = message.split(":");
        return new Order(arr[1], Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
    }
}
