package com.reactorintroduction.sec06;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;

public class Lec02HotPublisher {
    private static final Logger log = LoggerFactory.getLogger(Lec02HotPublisher.class);

    public static void main(String[] args) {
        var movieFlux = movieStream().share();

        Util.sleepSeconds(2);

        movieFlux.take(4).subscribe(Util.subscriber("Sam"));

        Util.sleepSeconds(3);

        movieFlux.take(3).subscribe(Util.subscriber("Mike"));

        Util.sleepSeconds(15);

    }

    private static Flux<String> movieStream() {
        return Flux.generate(
                () -> {
                    log.info("received the request");
                    return 1;
                },
                (state, sink) -> {
                    var scene = "movie scene " + state;
                    log.info("playing {}", scene);
                    sink.next(scene);
                    return ++state;
                })
                .take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }
}
