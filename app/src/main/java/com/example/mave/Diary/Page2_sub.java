package com.example.mave.Diary;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.mave.CreateRetrofit;
import com.example.mave.Dto.customQuestionDto.CreateCustomRequest;
import com.example.mave.Dto.customQuestionDto.CreateCustomResponse;
import com.example.mave.Dto.questionDto.TakeAllQuestionRequest;
import com.example.mave.Dto.questionDto.TakeAllQuestionResponse;
import com.example.mave.R;
import com.example.mave.repository.GroupRepository;
import com.example.mave.repository.QuestionRepository;
import com.example.mave.service.CustomQuestionRetrofitService;
import com.example.mave.service.QuestionRetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.royrodriguez.transitionbutton.utils.WindowUtils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mave.Diary.Create_Diary.TAG;

public class Page2_sub extends AppCompatActivity {


    public static Boolean isCalled = false;
    public static Boolean isAlarmed = false;


    private LocalTime questionTime;
    private TextView todayQuestion;
    private ListView listView;
    private ListViewAdapterForSub2 adapter;
    private Context mContext;
    GroupRepository groupDB;
    QuestionRepository questionDB;

    public static final String NOTIFICATION_CHANNEL_ID = "10001";


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_page2_sub);
        getSupportActionBar().hide();
//        WindowUtils.makeStatusbarTransparent(this);
        listView = findViewById(R.id.questionlist);

        mContext = this;

        adapter = new ListViewAdapterForSub2(Page2_sub.this);
        listView.setAdapter(adapter);

        groupDB = GroupRepository.getInstance();
        questionDB = QuestionRepository.getInstance();


        todayQuestion = (TextView) findViewById(R.id.todayQuestion); // 오늘의 질문 버튼

        // 설정 시간 넘었는지 체크
        questionTimeCheck();

        // 질문을 받아왔는지 체크
        allQuestionRequest(groupDB.getDiaryDate());


        todayQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Page2_sub_answer.class);
                Log.d(TAG, "답변 화면으로 가기 전!! " + todayQuestion.getText().toString());
                intent.putExtra("todayQuestion", todayQuestion.getText().toString());
                startActivity(intent);
                finish();
            }
        });


        FloatingActionButton FloatingButton = (FloatingActionButton) findViewById(R.id.customquestion);
        FloatingButton.setOnClickListener(new View.OnClickListener() { //플로팅버튼 눌렀을 때 이벤트
            @Override
            public void onClick(View v) {
                Create_Question dia = new Create_Question(Page2_sub.this);
                // 커스텀 다이얼로그 배경 투명
                dia.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dia.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dia.setDialogListener(new Create_Question.CustomDialogListener() {
                    @Override
                    public void onPositiveClicked(String custom_question) {
                        createCustomQuestion(custom_question);

                    }

                    @Override
                    public void onNegativeClicked() {
                        //취소버튼 눌렀을 경우 구현될 코드 작성
                    }
                });
                dia.show();
            }
        });

    }

    private void allQuestionRequest(Long questionNumber) {
        // 서버에서 질문 받아오기
        GroupRepository groupDB = GroupRepository.getInstance();
        QuestionRepository questionDB = QuestionRepository.getInstance();
        QuestionRetrofitService questionRetrofitService = CreateRetrofit.createRetrofit().create(QuestionRetrofitService.class);

        Log.d(TAG, "질문 받아옵니다!!");
        Log.d(TAG, "서버에 질문을 요청한 그룹 id는!? - " + groupDB.getGroupId().toString());
        Log.d(TAG, "우리 그룹은 며칠째인가!? - " + questionNumber);

        TakeAllQuestionRequest request = new TakeAllQuestionRequest(groupDB.getGroupId());
        Call<List<TakeAllQuestionResponse>> call = questionRetrofitService.takeAllQuestion(questionNumber, request);

        call.enqueue(new Callback<List<TakeAllQuestionResponse>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<TakeAllQuestionResponse>> call, Response<List<TakeAllQuestionResponse>> response) {
                if (response.isSuccessful()) {
                    List<TakeAllQuestionResponse> body = response.body();
                    Log.d(TAG, "response 성공!!");

                    Log.d(TAG, body.get(0).getQuestionContent());
                    Log.d(TAG, String.valueOf(body.size()));

                    questionDB.setTodayQuestion(body.get(0).getQuestionContent());
                    todayQuestion.setText(questionDB.getTodayQuestion());

                    List<String> questionList = new ArrayList<>();
                    for (int i = 1; i < body.size(); i++) {
                        Log.d(TAG, body.get(i).getQuestionContent());
                        adapter.addItem(body.get(i).getQuestionContent());
                    }
                    adapter.notifyDataSetChanged();

                } else {
                    Log.d(TAG, "response 실패 ㅠㅠ");

                }
            }

            @Override
            public void onFailure(Call<List<TakeAllQuestionResponse>> call, Throwable t) {
                Log.d(TAG, "onFailure => " + t.getMessage());
            }

        });
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void isAlarmCheck() {
        if (!isAlarmed) {
            Log.d(TAG, "알람이 울렸는가!? - " + String.valueOf(isAlarmed));
            NotificationSomethings();
            isAlarmed = true;
        } else {
            Log.d(TAG, "알람이 울렸는가!? - " + String.valueOf(isCalled));

        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void questionTimeCheck() {
        questionTime = GroupRepository.getInstance().getQuestionTime();
        Log.d(TAG, "설정한 시간은?? - " + questionTime);
        LocalTime nowTime = LocalTime.now();
        Log.d(TAG, "지금 시간은?? - " + nowTime);

        Log.d(TAG, String.valueOf(nowTime.isAfter(questionTime)));
        if (nowTime.isAfter(questionTime)) {
            isAlarmCheck();
        } else {
            Log.d(TAG, "아직 질문이 도착하지 않았습니다!"); // 아니면 말고 코드 넣으면 될 거 같습니다.
            Toast.makeText(getApplicationContext(), "아직 질문이 도착하지 않았습니다 ㅠㅠ", Toast.LENGTH_LONG).show();
        }
    }

    public void NotificationSomethings() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.mave_logo)) //BitMap 이미지 요구
                .setContentTitle("다이어리에 질문이 도착했습니다!")
                .setContentText("알림바를 눌러 확인하고 답장 해보세요!")
                // 더 많은 내용이라서 일부만 보여줘야 하는 경우 아래 주석을 제거하면 setContentText에 있는 문자열 대신 아래 문자열을 보여줌
                //.setStyle(new NotificationCompat.BigTextStyle().bigText("더 많은 내용을 보여줘야 하는 경우..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        //OREO API 26 이상에서는 채널 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setSmallIcon(R.drawable.mave_logo2); //mipmap 사용시 Oreo 이상에서 시스템 UI 에러남
            CharSequence channelName = "노티페케이션 채널";
            String description = "오레오 이상을 위한 것임";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance);
            channel.setDescription(description);
            // 노티피케이션 채널을 시스템에 등록
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        } else
            builder.setSmallIcon(R.mipmap.ic_launcher); // Oreo 이하에서 mipmap 사용하지 않으면 Couldn't create icon: StatusBarIcon 에러남
        assert notificationManager != null;
        notificationManager.notify(1234, builder.build()); // 고유숫자로 노티피케이션 동작시킴
    }

    private void createCustomQuestion(String customQuestion) {
        CustomQuestionRetrofitService customQuestionRetrofitService = CreateRetrofit.createRetrofit().create(CustomQuestionRetrofitService.class);
        CreateCustomRequest request = new CreateCustomRequest(customQuestion,
                GroupRepository.getInstance().getDiaryDate(),
                GroupRepository.getInstance().getGroupId());
        Call<CreateCustomResponse> call = customQuestionRetrofitService.createCustomQuestion(request);

        call.enqueue(new Callback<CreateCustomResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<CreateCustomResponse> call, Response<CreateCustomResponse> response) {
                if (response.isSuccessful()) {
                    CreateCustomResponse body = response.body();
                    Log.d(TAG, "response 성공!!");
                    Log.d(TAG, "Custom 질문 보내기 성공!! ");
                    QuestionRepository.getInstance().setQuestion(customQuestion);

                } else {

                    Log.d(TAG, "아직 질문이 도착하지 않았습니다!");
                    Toast.makeText(getApplicationContext(), "아직 질문이 도착하지 않았습니다 ㅠㅠ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CreateCustomResponse> call, Throwable t) {
                Log.d(TAG, "onFailure => " + t.getMessage());
            }

        });
    }
}


