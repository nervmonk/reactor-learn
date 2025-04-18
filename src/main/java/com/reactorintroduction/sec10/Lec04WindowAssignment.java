package com.reactorintroduction.sec10;

import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec10.window.FileWriter;

import reactor.core.publisher.Flux;

public class Lec04WindowAssignment {
    public static void main(String[] args) {

        var counter = new AtomicInteger(0);
        var fileNameFormat = "src/main/resources/sec10/file%d.txt";
        eventStream()
                .window(Duration.ofMillis(1800))
                .flatMap(flux -> FileWriter.create(flux, Path.of(fileNameFormat.formatted(counter.incrementAndGet()))))
                .subscribe();

        Util.sleepSeconds(60);

    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> "event-" + (i + 1));
    }
}
