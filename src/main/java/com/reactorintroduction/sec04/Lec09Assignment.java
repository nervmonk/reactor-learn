package com.reactorintroduction.sec04;

import java.nio.file.Path;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec04.assignment.FileReaderServiceImpl;

public class Lec09Assignment {
    public static void main(String[] args) {
        var path = Path.of("src/main/resources/sec04/file.txt");
        var fileReaderService = new FileReaderServiceImpl();
        fileReaderService.read(path)
                .take(3)
                .subscribe(Util.subscriber());
    }
}
