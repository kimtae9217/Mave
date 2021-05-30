package com.example.mave.repository;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class GroupRepository {

    private static Long date = 0l;

    private static final GroupRepository groupRepository = new GroupRepository();

    private static Map<String,String> groupInfo = new HashMap<>();
    private static  Map<String, LocalTime> questionTime = new HashMap<>();

    private String groupId;
    private String groupName;

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

    public void setQuestionTime(LocalTime localTime){
        questionTime.put("questionTime",localTime);
    }

    public LocalTime getQuestionTime(){
        return questionTime.get("questionTime");
    }

    public void plusDate(){
        date++;
    }

    public Long getDate(){
        return date;
    }

}
