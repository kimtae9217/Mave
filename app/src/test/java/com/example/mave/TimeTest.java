package com.example.mave;

import org.junit.Test;

import java.time.LocalTime;

public class TimeTest {

    @Test
    public void timeTest(){
        LocalTime time = LocalTime.of(19, 10);
        LocalTime nowtime = LocalTime.now();

        boolean after = time.isAfter(nowtime);
        System.out.println("after = " + after);
    }
}
