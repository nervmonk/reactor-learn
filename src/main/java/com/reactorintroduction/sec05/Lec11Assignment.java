package com.reactorintroduction.sec05;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec05.assignments.ExternalServiceClient;

public class Lec11Assignment {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        for (int i = 0; i < 5; i++) {
            client.getProductName(i)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(3);
    }
}
