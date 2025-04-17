package com.reactorintroduction.sec09;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec09.assignment.ExternalServiceClient;

public class Lec08ZipAssignment {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        for (int i = 0; i < 10; i++) {
            client.getProduct(i).subscribe(Util.subscriber());
        }
    }
}
