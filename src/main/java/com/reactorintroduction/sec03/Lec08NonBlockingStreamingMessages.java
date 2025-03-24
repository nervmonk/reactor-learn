package com.reactorintroduction.sec03;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec03.client.ExternalServiceClient;

public class Lec08NonBlockingStreamingMessages {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        client.getNames()
        .subscribe(Util.subscriber("sub-1"));

        client.getNames()
        .subscribe(Util.subscriber("sub-2"));

        Util.sleepSeconds(10);
    }
}
