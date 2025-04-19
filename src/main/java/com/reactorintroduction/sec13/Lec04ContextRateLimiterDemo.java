package com.reactorintroduction.sec13;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec13.client.ExternalServiceClient;

import reactor.util.context.Context;

public class Lec04ContextRateLimiterDemo {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        for (int i = 0; i < 20; i++) {
            client.getBook()
                    .contextWrite(Context.of("user", "Ryan"))
                    .subscribe(Util.subscriber());
            Util.sleepSeconds(1);
        }

        Util.sleepSeconds(5);
    }
}
