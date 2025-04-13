package com.reactorintroduction.sec09.helper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;

public class NameGenerator {
    private static final Logger log = LoggerFactory.getLogger(NameGenerator.class);

    private final List<String> redis = new ArrayList<>(); // for demo

    public Flux<String> generateNames() {
        return Flux.generate(sink -> {
            log.info("generating name");
            Util.sleepSeconds(1);
            var name = Util.faker().name().firstName();
            redis.add(name);
            sink.next(name);
        })
                .startWith(redis)
                .cast(String.class);
    }
}
