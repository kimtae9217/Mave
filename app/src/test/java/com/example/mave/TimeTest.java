package com.example.mave;

import com.example.mave.Dto.questionDto.TakeAllQuestionResponse;

import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TimeTest {

    @Test
    public void timeTest(){
        LocalTime time = LocalTime.of(19, 10);
        LocalTime nowtime = LocalTime.now();

        boolean after = time.isAfter(nowtime);
        System.out.println("after = " + after);
    }

    @Test
    public void streamTest(){
        TakeAllQuestionResponse first = new TakeAllQuestionResponse("1번");
        TakeAllQuestionResponse second = new TakeAllQuestionResponse("2번");
        ArrayList<TakeAllQuestionResponse> list = new ArrayList<>();
        list.add(first);
        list.add(second);

        List<String> collect = list.stream().map(o -> o.getQuestionContent()).collect(Collectors.toList());
        for (String s : collect) {
            System.out.println("s = " + s);
        }

    }
}
