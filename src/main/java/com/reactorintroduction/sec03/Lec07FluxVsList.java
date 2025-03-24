package com.reactorintroduction.sec03;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec01.subscriber.SubscriberImpl;
import com.reactorintroduction.sec03.helper.NameGenerator;

public class Lec07FluxVsList {
    public static void main(String[] args) {
        // var list = NameGenerator.getNamesList(10);
        // System.out.println(list);

        var subscriber = new SubscriberImpl();

        NameGenerator.getNamesFlux(10)
                .log()
                .subscribe(subscriber);

        subscriber.getSubscription().request(3);
    }
}
