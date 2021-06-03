package com.example.mave.repository;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class GroupRepository {

    public static Long diaryDate = 0l;
    public static Long completeDate = 0l;

    private static final GroupRepository groupRepository = new GroupRepository();

    private static Map<String,String> groupInfo = new HashMap<>();
    private static  Map<String, LocalTime> questionTime = new HashMap<>();
    private static Map<String, Integer> user_Set_hour = new HashMap<>();
    private static Map<String, Integer> user_Set_minute = new HashMap<>();

    private String groupId;
    private String groupName;
    private String flowerStatus;

    private GroupRepository(){

    }

    public static GroupRepository getInstance(){
        return groupRepository;
    }

    public Long getGroupId() {
        return Long.parseLong(groupInfo.get("groupId"));
    }

    public void setGroupId(Long groupId) {
        groupInfo.put("groupId",groupId.toString());
    }

    public String getGroupName() {
        return groupInfo.get("groupName");
    }

    public void setGroupName(String groupName) {
       groupInfo.put("groupName",groupName);
    }

    public int getFlowerStatus() {
        return Integer.parseInt(groupInfo.get("flowerStatus"));
    }

    public void setFlowerStatus(int flowerStatus) {
        groupInfo.put("flowerStatus",String.valueOf(flowerStatus));
    }


    public void setQuestionTime(LocalTime localTime){
        questionTime.put("questionTime",localTime);
    }

    public LocalTime getQuestionTime(){
        return questionTime.get("questionTime");
    }

    public void setDiaryDate(Long date){
        this.diaryDate = date;
    }

    public Long getDiaryDate(){
        return diaryDate;
    }

    public void setCompleteDate(Long date){
        this.completeDate = date;
    }

    public Long getCompleteDate(){
        return completeDate;
    }


    public void setuser_Set_hour(int user_set_hour){
        user_Set_hour.put("user_set_hour", user_set_hour);

    }
    public int getuser_Set_hour(){
        int user_set_hour = user_Set_hour.get("user_set_hour");
        return user_set_hour;
    }

    public void setuser_Set_minute(int user_set_minute){
        user_Set_minute.put("user_set_minute", user_set_minute);

    }
    public int getuser_Set_minute(){
        int user_set_minute = user_Set_minute.get("user_set_minute");
        return user_set_minute;
    }

}
