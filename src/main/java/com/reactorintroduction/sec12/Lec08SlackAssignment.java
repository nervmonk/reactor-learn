package com.reactorintroduction.sec12;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec12.assignment.SlackMember;
import com.reactorintroduction.sec12.assignment.SlackRoom;

public class Lec08SlackAssignment {

    public static void main(String[] args) {
        var room = new SlackRoom("Reactor");

        var sam = new SlackMember("Sam");
        var jake = new SlackMember("Jake");
        var mike = new SlackMember("Mike");

        room.addMember(sam);
        room.addMember(jake);

        sam.says("Hi all..");
        Util.sleepSeconds(4);

        jake.says("hey!");
        sam.says("I simply wanted to say hi..");
        Util.sleepSeconds(4);

        room.addMember(mike);
        mike.says("Hey guys..glad to be here...");
    }
}
