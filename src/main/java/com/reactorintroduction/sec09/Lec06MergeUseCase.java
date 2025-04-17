package com.reactorintroduction.sec09;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec09.helper.Kayak;

public class Lec06MergeUseCase {
    public static void main(String[] args) {
        Kayak.getFlights().subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }
}
