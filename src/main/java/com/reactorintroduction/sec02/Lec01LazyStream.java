package com.reactorintroduction.sec02;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec01LazyStream {
    private static final Logger log = LoggerFactory.getLogger(Lec01LazyStream.class);

    public static void main(String[] args) {
        Stream.of(1)
        .peek(i -> log.info("received: {}", i));
    }
}
