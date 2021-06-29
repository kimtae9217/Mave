package com.example.mave.Diary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mave.CreateRetrofit;
import com.example.mave.Dto.AnswerDto.AllAnswerRequest;
import com.example.mave.Dto.AnswerDto.AllAnswerResponse;
import com.example.mave.Dto.AnswerDto.RegistAnswerRequest;
import com.example.mave.Dto.AnswerDto.RegistAnswerResponse;
import com.example.mave.Dto.customQuestionDto.CreateCustomRequest;
import com.example.mave.Dto.customQuestionDto.CreateCustomResponse;
import com.example.mave.Dto.customQuestionDto.UseCustomRequest;
import com.example.mave.Dto.customQuestionDto.UseCustomResponse;
import com.example.mave.Dto.questionDto.TakeAllQuestionRequest;
import com.example.mave.Dto.questionDto.TakeAllQuestionResponse;
import com.example.mave.Dto.questionDto.TakeQuestionRequest;
import com.example.mave.Dto.questionDto.TakeQuestionResponse;
import com.example.mave.PreferenceManager;
import com.example.mave.R;
import com.example.mave.activities.MainActivity;
import com.example.mave.repository.AnswerRepository;
import com.example.mave.repository.GroupRepository;
import com.example.mave.repository.MemberRepository;
import com.example.mave.repository.QuestionRepository;
import com.example.mave.service.AnswerRetrofitService;
import com.example.mave.service.CustomQuestionRetrofitService;
import com.example.mave.service.QuestionRetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.royrodriguez.transitionbutton.utils.WindowUtils;

import java.lang.reflect.Member;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mave.Diary.Create_Diary.TAG;
import static com.example.mave.repository.GroupRepository.*;


public class Page2_sub_answer extends AppCompatActivity {


    private ListView listView;
    private Button btn_add, btn_custom;
    private EditText edt_title;
    private ListViewAdapter adapter;
    static int count = 0;
    private ImageView flower;
    private Context mContext;
    private TextView TodayQuestion;
    private ImageButton calendar;
    SharedPreferences pref;
    SharedPreferences.Editor editor;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_page2_sub_answer);
        getSupportActionBar().hide();
//        WindowUtils.makeStatusbarTransparent(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); // EditText 사용시 키보드 자판이 UI 가릴 때 영향 안주는 것
        edt_title = (EditText) findViewById(R.id.edt_answer);
        btn_add = (Button) findViewById(R.id.btn_answer);
        listView = (ListView) findViewById(R.id.listMemo);
        flower = (ImageView) findViewById(R.id.diary_flower);
        TodayQuestion = (TextView) findViewById(R.id.todayQuestion);
        btn_custom = (Button) findViewById(R.id.customquestion);
        calendar = (ImageButton) findViewById(R.id.imageButton2);
        mContext = this;
        Intent intent = getIntent();
        String todayQuestion = intent.getStringExtra("todayQuestion");


        adapter = new ListViewAdapter(Page2_sub_answer.this);
        listView.setAdapter(adapter);


        Log.d(TAG, "답변 화면 - 질문 가져오자!!");
        TodayQuestion.setText(todayQuestion);


        Log.d(TAG, "답변 화면 - 답변 가져오자!!");
        takeAllAnswer(adapter);

        // 데이터 추가하기
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.addItem(MemberRepository.getInstance().getUserId(), edt_title.getText().toString());
                registAnswer();
                edt_title.setText("");
                count++;
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void registAnswer() {

        AnswerRetrofitService answerRetrofitService = CreateRetrofit.createRetrofit().create(AnswerRetrofitService.class);
        RegistAnswerRequest request = new RegistAnswerRequest(MemberRepository.getInstance().getUserId(), getInstance().getGroupId(), edt_title.getText().toString());
        Call<RegistAnswerResponse> call = answerRetrofitService.registAnswer(getInstance().getCompleteDate(), request);

        call.enqueue(new Callback<RegistAnswerResponse>() {
            @Override
            public void onResponse(Call<RegistAnswerResponse> call, Response<RegistAnswerResponse> response) {
                if (response.isSuccessful()) {
                    RegistAnswerResponse body = response.body();
                    Log.d(TAG, "response 성공!!");


                    if (body.getFinish()) {
                        completeDate++;
                        checkCustom();
                        Level_Up_Dialog dig_2 = new Level_Up_Dialog(Page2_sub_answer.this, Level_Up_Dialog.class);
                        // 커스텀 다이얼로그 배경 투명
                        dig_2.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dig_2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dig_2.show();
                    } else {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Log.d(TAG, "response 실패 ㅠㅠ");

                }
            }


            @Override
            public void onFailure(Call<RegistAnswerResponse> call, Throwable t) {
                Log.d(TAG, "onFailure => " + t.getMessage());
            }
        });


    }

    public void takeAllAnswer(ListViewAdapter adapter) {
        AnswerRetrofitService answerRetrofitService = CreateRetrofit.createRetrofit().create(AnswerRetrofitService.class);
        AllAnswerRequest request = new AllAnswerRequest(getInstance().getGroupId());
        Call<List<AllAnswerResponse>> call = answerRetrofitService.allAnswer(getInstance().getDiaryDate(), request);

        call.enqueue(new Callback<List<AllAnswerResponse>>() {
            @Override
            public void onResponse(Call<List<AllAnswerResponse>> call, Response<List<AllAnswerResponse>> response) {
                if (response.isSuccessful()) {
                    List<AllAnswerResponse> body = response.body();
                    Log.d(TAG, "response 성공!!");
                    for (AllAnswerResponse allAnswerResponse : body) {
                        String answerContent = allAnswerResponse.getAnswerContent();
                        String userId = allAnswerResponse.getUserId();
                        if (userId.equals(MemberRepository.getInstance().getUserId())) {
                            edt_title.setVisibility(View.GONE);
                            btn_add.setVisibility(View.GONE);
                        }
                        adapter.addItem(userId, answerContent);

                    }
                    adapter.notifyDataSetChanged();

                } else {
                    Log.d(TAG, "response 실패 ㅠㅠ");

                }
            }

            @Override
            public void onFailure(Call<List<AllAnswerResponse>> call, Throwable t) {
                Log.d(TAG, "onFailure => " + t.getMessage());
            }
        });


    }

    private void questionRequest(Long diaryDate) {
        GroupRepository groupDB = getInstance();
        // 서버에서 질문 받아오기
        QuestionRetrofitService questionRetrofitService = CreateRetrofit.createRetrofit().create(QuestionRetrofitService.class);

        Log.d(TAG, "질문 생성합니다!!");
        Log.d(TAG, "서버에 질문을 요청한 그룹 id는!? - " + groupDB.getGroupId());
        Log.d(TAG, "우리 그룹은 며칠째인가!? - " + diaryDate);

        TakeQuestionRequest request = new TakeQuestionRequest(groupDB.getGroupId());
        Call<TakeQuestionResponse> call = questionRetrofitService.takeQuestion(diaryDate, request);

        call.enqueue(new Callback<TakeQuestionResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<TakeQuestionResponse> call, Response<TakeQuestionResponse> response) {
                if (response.isSuccessful()) {
                    TakeQuestionResponse body = response.body();
                    Log.d(TAG, "response 성공!!");


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

    private void checkCustom() throws NullPointerException {
        CustomQuestionRetrofitService customQuestionRetrofitService = CreateRetrofit.createRetrofit().create(CustomQuestionRetrofitService.class);
        CreateCustomRequest request = new CreateCustomRequest("customQuestion",
                GroupRepository.getInstance().getDiaryDate(),
                GroupRepository.getInstance().getGroupId());
        Call<CreateCustomResponse> call = customQuestionRetrofitService.checkCustom(request);

        call.enqueue(new Callback<CreateCustomResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<CreateCustomResponse> call, Response<CreateCustomResponse> response) {
                if (response.isSuccessful()) {
                    CreateCustomResponse body = response.body();
                    Log.d(TAG, "response 성공!!");
                    Log.d(TAG, "Custom 질문 체크!!");

                    if (body.getQuestionContent() != null) {
                        Log.d(TAG, "Custom 질문 있다!!");
                        Log.d(TAG, "커스텀 질문 가져오기");
                        customQuestionRequest();
                    } else {
                        Log.d(TAG, "그냥 질문 가져오기");
                        questionRequest(completeDate);
                    }


                } else {
                    Log.d(TAG, "response 실패 ㅠㅠ");

                }
            }

            @Override
            public void onFailure(Call<CreateCustomResponse> call, Throwable t) {
                Log.d(TAG, "onFailure => " + t.getMessage());
            }

        });
    }

    private void customQuestionRequest() {
        CustomQuestionRetrofitService customQuestionRetrofitService = CreateRetrofit.createRetrofit().create(CustomQuestionRetrofitService.class);
        UseCustomRequest request = new UseCustomRequest(GroupRepository.getInstance().getGroupId());
        Call<UseCustomResponse> call = customQuestionRetrofitService.useCustomQuestion(diaryDate, request);

        call.enqueue(new Callback<UseCustomResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<UseCustomResponse> call, Response<UseCustomResponse> response) {
                if (response.isSuccessful()) {
                    UseCustomResponse body = response.body();
                    Log.d(TAG, "response 성공!!");
                    Log.d(TAG, "Custom 질문 만들어주세요!!");

                } else {
                    Log.d(TAG, "response 실패 ㅠㅠ");

                }
            }

            @Override
            public void onFailure(Call<UseCustomResponse> call, Throwable t) {
                Log.d(TAG, "onFailure => " + t.getMessage());
            }

        });
    }


}