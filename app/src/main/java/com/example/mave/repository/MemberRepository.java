package com.example.mave.repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MemberRepository {

    private static final MemberRepository memberRepository = new MemberRepository();

    private static  Map<String,String> userInfo = new HashMap<>();

    private  MemberRepository() {

    }

    public static MemberRepository getInstance(){
        return  memberRepository;
    }

    public void setUserId(String userId){
        userInfo.put("userId",userId);
    }

    public String getUserId(){
        String userId = userInfo.get("userId");
        return userId;
    }

}
