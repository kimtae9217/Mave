package com.example.mave.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionRepository {

    private static final QuestionRepository questionRepository = new QuestionRepository();

    private static Map<String, String> customQuestionInfo = new HashMap<>();
    private static Map<String, String> todayQuestionInfo = new HashMap<>();


    private QuestionRepository() {
    }

    public static QuestionRepository getInstance(){
        return questionRepository;
    }

   public String getTodayQuestion(){
        return todayQuestionInfo.get("questionContent");
   }

   public void setTodayQuestion(String questionContent){
       todayQuestionInfo.put("questionContent",questionContent);

   }

   public void setQuestion(String customQuestion){
       customQuestionInfo.put("customQuestion", customQuestion);
   }

   public String getQuestion(){
        return customQuestionInfo.get("customQuestion");
   }
}
