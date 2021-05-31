package com.example.mave.Diary;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mave.CreateRetrofit;
import com.example.mave.Dto.groupDto.CreateGroupRequest;
import com.example.mave.Dto.groupDto.CreateGroupResponse;
import com.example.mave.R;
import com.example.mave.repository.GroupRepository;
import com.example.mave.repository.MemberRepository;
import com.example.mave.service.GroupRetrofitService;

import java.security.acl.Group;
import java.time.LocalTime;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Create_Diary extends Dialog implements View.OnClickListener {

    static final String TAG = "Mave";
    private Button positiveButton;
    private Button negativeButton;
    private EditText editName;
    private Context context;
    private CustomDialogListener customDialogListener;
    private String diaryName;
    TimePickerDialog timePickerDialog;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Calendar c;
    int hour_;
    int minute_;

    public Create_Diary(Context context) {
        super(context);
        this.context = context;
    }

    interface CustomDialogListener{
        void onPositiveClicked(String diary_name);
        void onNegativeClicked();
    }
    public void setDialogListener(CustomDialogListener customDialogListener){
        this.customDialogListener = customDialogListener;
    }
@Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_diary);
//init
        positiveButton = (Button)findViewById(R.id.btnPositive);
        negativeButton = (Button)findViewById(R.id.btnNegative);
        editName = (EditText)findViewById(R.id.editName);

        //버튼 클릭 리스너 등록
        positiveButton.setOnClickListener(this);
        negativeButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR);
        int mMinute = c.get(Calendar.MINUTE);
        switch (v.getId()){
            case R.id.btnPositive: //확인 버튼을 눌렀을 때
                //각각의 변수에 EidtText에서 가져온 값을 저장
                diaryName = editName.getText().toString();
                //인터페이스의 함수를 호출하여 변수에 저장된 값들을 Activity로 전달

                customDialogListener.onPositiveClicked(diaryName);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override

                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                    LocalTime questionTime = LocalTime.of(hourOfDay, minute);
                                    GroupRepository instance = GroupRepository.getInstance();
                                    // 질문 받을 시간 내부 db에 저장
                                    instance.setQuestionTime(questionTime);
                                    instance.plusDate();
                                    Log.d(TAG,instance.getQuestionTime().toString());
                                    Log.d(TAG,instance.getDate().toString());
                                Calendar c = Calendar.getInstance();
                                hour_ = c.get(Calendar.HOUR_OF_DAY);
                                minute_ = c.get(Calendar.MINUTE);

                                Toast.makeText(getContext(), hourOfDay + "시" + minute + "분", Toast.LENGTH_SHORT).show();
                            }
                        },mHour, mMinute, DateFormat.is24HourFormat(getContext()));
                timePickerDialog.show();

                dismiss();
                break;
            case R.id.btnNegative: //취소 버튼을 눌렀을 때
                cancel();
                break;
        }

        Log.d(TAG,diaryName);
        // 그룹 생성 api 요청!!
        requestCreateGroup();
    }

    private void requestCreateGroup() {

        GroupRetrofitService groupRetrofitService = CreateRetrofit.createRetrofit().create(GroupRetrofitService.class);
        MemberRepository.getInstance().setUserId("hello1");
        String userId = MemberRepository.getInstance().getUserId();
        CreateGroupRequest request = new CreateGroupRequest(userId,diaryName);
        Call<CreateGroupResponse> call = groupRetrofitService.createGroup(request);

        call.enqueue(new Callback<CreateGroupResponse>() {
            @Override
            public void onResponse(Call<CreateGroupResponse> call, Response<CreateGroupResponse> response) {
                if (response.isSuccessful()) {
                    CreateGroupResponse body = response.body();
                    Log.d(TAG, "response 성공!!");

                    // 그룹 id를 내부 db에 저장
                    GroupRepository.getInstance().setGroupId(body.getGroupId());

                    // 그룹 이름을 내부 db에 저장
                    GroupRepository.getInstance().setGroupName(diaryName);

                } else {
                    Log.d(TAG, "response 실패 ㅠㅠ");

                }
            }

            @Override
            public void onFailure(Call<CreateGroupResponse> call, Throwable t) {
                Log.d(TAG, "onFailure => " + t.getMessage());
            }
        });
    }
}