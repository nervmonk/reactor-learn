package com.reactorintroduction.sec11;

import java.rmi.ServerError;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec11.client.ExternalServiceClient;

import reactor.util.retry.Retry;

public class Lec03ExternalServiceDemo {

    private static Logger log = LoggerFactory.getLogger(Lec03ExternalServiceDemo.class);

    public static void main(String[] args) {
        retry();
        Util.sleepSeconds(10);
    }

    private static void repeat() {
        var client = new ExternalServiceClient();
        client.getCountry()
                .repeat()
                .takeUntil(c -> c.equalsIgnoreCase("poland"))
                .subscribe(Util.subscriber());
    }

    private static void retry() {
        var client = new ExternalServiceClient();
        client.getProductName(1)
                .retryWhen(retryOnServerError())
                .subscribe(Util.subscriber());

    }

    private static Retry retryOnServerError() {
        return Retry.fixedDelay(20, Duration.ofSeconds(1))
                .filter(ex -> ServerError.class.equals(ex.getClass()))
                .doBeforeRetry(rs -> log.info("retrying {}", rs.failure().getMessage()));

    }
}
