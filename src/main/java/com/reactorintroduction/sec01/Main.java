package com.reactorintroduction.sec01;

import java.time.Duration;

import com.reactorintroduction.sec01.publisher.PublisherImpl;
import com.reactorintroduction.sec01.subscriber.SubscriberImpl;

/*
 1. Publisher does not produce data unless subscriber requests for it.
 2. Publisher will produce only <= subscriber requested items. Publisher can also produce 0 items!
 3. Subscriber can cancel the subscription. Producer should stop at that moment as subscriber is no longer interested in consuming the data
 4. Producer can send the error signal to indicate something is wrong
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        demo4();
    }

    private static void demo1(){
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
    }

    private static void demo2() throws InterruptedException{
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);

        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);

    }

    private static void demo3() throws InterruptedException{
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
    }

    private static void demo4() throws InterruptedException{
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(11);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
    }
}