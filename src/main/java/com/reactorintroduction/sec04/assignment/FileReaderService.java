package com.reactorintroduction.sec04.assignment;

import java.nio.file.Path;

import reactor.core.publisher.Flux;

public interface FileReaderService {
    Flux<String> read(Path path);
}
