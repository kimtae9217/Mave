package com.example.mave.Diary;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.mave.CreateRetrofit;
import com.example.mave.Dto.groupDto.CreateGroupRequest;
import com.example.mave.Dto.groupDto.CreateGroupResponse;
import com.example.mave.Dto.questionDto.TakeQuestionRequest;
import com.example.mave.Dto.questionDto.TakeQuestionResponse;
import com.example.mave.R;
import com.example.mave.repository.GroupRepository;
import com.example.mave.repository.MemberRepository;
import com.example.mave.repository.QuestionRepository;
import com.example.mave.service.GroupRetrofitService;
import com.example.mave.service.QuestionRetrofitService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

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


    public Create_Diary(Context context) {
        super(context);
        this.context = context;
    }

    interface CustomDialogListener {
        void onPositiveClicked(String diary_name);

        void onNegativeClicked();
    }

    public void setDialogListener(CustomDialogListener customDialogListener) {
        this.customDialogListener = customDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_diary);
//init
        positiveButton = (Button) findViewById(R.id.btnPositive);
        negativeButton = (Button) findViewById(R.id.btnNegative);
        editName = (EditText) findViewById(R.id.editName);

        //버튼 클릭 리스너 등록
        positiveButton.setOnClickListener(this);
        negativeButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR);
        int mMinute = c.get(Calendar.MINUTE);
        switch (v.getId()) {
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
                                instance.setuser_Set_hour(hourOfDay);
                                instance.setuser_Set_minute(minute);

                                Log.d(TAG, "질문 받을 시간은 !? - " + instance.getQuestionTime().toString());

                                requestCreateGroup(hourOfDay, minute);

                                Log.d(TAG, "초기 질문 생성 !! - ");

                                Toast.makeText(getContext(), hourOfDay + "시" + minute + "분", Toast.LENGTH_SHORT).show();
                            }
                        }, mHour, mMinute, false);

                timePickerDialog.show();

                dismiss();
                break;
            case R.id.btnNegative: //취소 버튼을 눌렀을 때
                cancel();
                break;
        }

        Log.d(TAG, "설정한 그룹 이름은!? - " + diaryName);


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void requestCreateGroup(int hourOfDay, int minute) {
        LocalDateTime fullDateTime = LocalDateTime.of(
                LocalDateTime.now().getYear(),
                LocalDateTime.now().getMonth(),
                LocalDateTime.now().getDayOfMonth(),
                hourOfDay,
                minute
        );

        String format = fullDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


        GroupRetrofitService groupRetrofitService = CreateRetrofit.createRetrofit().create(GroupRetrofitService.class);
        MemberRepository.getInstance().setUserId("hello1");
        String userId = MemberRepository.getInstance().getUserId();
        CreateGroupRequest request = new CreateGroupRequest(userId, diaryName,format);

        Call<CreateGroupResponse> call = groupRetrofitService.createGroup(request);

        call.enqueue(new Callback<CreateGroupResponse>() {
            @Override
            public void onResponse(Call<CreateGroupResponse> call, Response<CreateGroupResponse> response) {
                if (response.isSuccessful()) {
                    CreateGroupResponse body = response.body();
                    Log.d(TAG, "response 성공!!");

                    // 그룹 id를 내부 db에 저장
                    GroupRepository.getInstance().setGroupId(body.getGroupId());
                    Log.d(TAG, "그룹 id 내부 db에 저장 완료!");

                    // 그룹 이름을 내부 db에 저장
                    GroupRepository.getInstance().setGroupName(diaryName);
                    Log.d(TAG, "그룹 이름 내부 db에 저장 완료!");

                    GroupRepository.getInstance().setDiaryDate(body.getDiaryDate());
                    Log.d(TAG, "그룹 D-Day 내부 db에 저장 완료!");

                    questionRequest();

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

    private void questionRequest() {
        // 서버에서 질문 받아오기 서버 DB에 질문을 넣는다.
        QuestionRetrofitService questionRetrofitService = CreateRetrofit.createRetrofit().create(QuestionRetrofitService.class);
        GroupRepository groupDB = GroupRepository.getInstance();
        QuestionRepository questionDB = QuestionRepository.getInstance();

        Log.d(TAG, "서버에 질문을 요청한 그룹 id는!? - " + groupDB.getGroupId());
        Log.d(TAG, "우리 그룹은 며칠째인가!? - " + groupDB.getDiaryDate());

        TakeQuestionRequest request = new TakeQuestionRequest(groupDB.getGroupId());
        Call<TakeQuestionResponse> call = questionRetrofitService.takeQuestion(groupDB.getDiaryDate(), request);

        call.enqueue(new Callback<TakeQuestionResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<TakeQuestionResponse> call, Response<TakeQuestionResponse> response) {
                if (response.isSuccessful()) {
                    TakeQuestionResponse body = response.body();
                    Log.d(TAG, "response 성공!!");
                    questionDB.setTodayQuestion(body.getQuestionContent());

                } else {
                    Log.d(TAG, "response 실패 ㅠㅠ");

                }
            }


            @Override
            public void onFailure(Call<TakeQuestionResponse> call, Throwable t) {
                Log.d(TAG, "onFailure => " + t.getMessage());
            }
        });
    }
}