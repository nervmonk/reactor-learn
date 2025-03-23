package com.reactorintroduction.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Mono;

public class Lec09PublisherCreateVsExecution {
    private static final Logger log = LoggerFactory.getLogger(Lec09PublisherCreateVsExecution.class);

    public static void main(String[] args) {
        getName();
    }

    private static Mono<String> getName() {
        log.info("entered the method");
        return Mono.fromSupplier(() -> {
            log.info("generating name");
            return Util.faker().name().firstName();
        });
    }
}
