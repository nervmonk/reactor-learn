package com.reactorintroduction.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec05.assignments.ExternalServiceClient;

public class Lec11NonBlockingIO {
    private static final Logger log = LoggerFactory.getLogger(Lec11NonBlockingIO.class);

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        log.info("starting");

        for (int i = 1; i <= 100; i++) {
            client.getProductName(i).subscribe(Util.subscriber());
        }

        Util.sleepSeconds(2);
    }
}
