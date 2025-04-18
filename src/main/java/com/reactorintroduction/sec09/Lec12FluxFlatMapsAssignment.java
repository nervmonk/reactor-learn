package com.reactorintroduction.sec09;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec09.assignment.ExternalServiceClient;

import reactor.core.publisher.Flux;

public class Lec12FluxFlatMapsAssignment {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        Flux.range(1, 10)
                .flatMap(client::getProduct)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(2);
    }
}
