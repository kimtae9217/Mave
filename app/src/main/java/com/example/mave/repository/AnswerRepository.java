package com.example.mave.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnswerRepository {

    private static final AnswerRepository answerRepository = new AnswerRepository();

    private static Map<String, List<String>> answerInfo = new HashMap<>();

    ArrayList<String> answerList = new ArrayList<>();

    private AnswerRepository() {
    }

    public static AnswerRepository getInstance(){
        return answerRepository;
    }

   public List<String> getAnswerList(){
        return answerInfo.get("answerList");
   }

   public void setAnswerList(String answer){
        answerList.add(answer);
        answerInfo.put("answerList",answerList);
   }
}
