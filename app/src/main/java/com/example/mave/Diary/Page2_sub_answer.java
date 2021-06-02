package com.example.mave.Diary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

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
import com.example.mave.PreferenceManager;
import com.example.mave.R;
import com.example.mave.repository.AnswerRepository;
import com.example.mave.repository.GroupRepository;
import com.example.mave.repository.QuestionRepository;
import com.example.mave.service.AnswerRetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); // EditText 사용시 키보드 자판이 UI 가릴 때 영향 안주는 것
        edt_title = (EditText) findViewById(R.id.edt_answer);
        btn_add = (Button) findViewById(R.id.btn_answer);
        listView = (ListView) findViewById(R.id.listMemo);
        flower = (ImageView) findViewById(R.id.diary_flower);
        TodayQuestion = (TextView) findViewById(R.id.todayQuestion);
        //btn_custom = (Button) findViewById(R.id.customquestion);
        calendar = (ImageButton) findViewById(R.id.imageButton2);
        mContext = this;
        Intent intent = getIntent();
        String todayQuestion = intent.getStringExtra("todayQuestion");

        adapter = new ListViewAdapter(Page2_sub_answer.this);
        listView.setAdapter(adapter);


        Log.d(TAG, "답변 화면 - 질문 가져오자!!");
        TodayQuestion.setText(todayQuestion);

        Log.d(TAG,"답변 화면 - 답변 가져오자!!");
        takeAllAnswer(adapter);

        /*calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Level_Up_Dialog dig_2 = new Level_Up_Dialog(Page2_sub_answer.this, Level_Up_Dialog.class);
                // 커스텀 다이얼로그 배경 투명
                dig_2.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dig_2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dig_2.show();
            }
        });*/


        // 데이터 추가하기
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.addItem(edt_title.getText().toString());
                registAnswer(edt_title);
                edt_title.setText("");
                count++;
                /*SharedPreferences sharedPreferences = getSharedPreferences("ansewercount",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("addcount", count);
                editor.commit();*/
                PreferenceManager.setInt(mContext, "test", count);
                if(count > 3) {
                    Level_Up_Dialog dig_2 = new Level_Up_Dialog(Page2_sub_answer.this, Level_Up_Dialog.class);
                    // 커스텀 다이얼로그 배경 투명
                    dig_2.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dig_2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dig_2.show();
                    count = 0;
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void registAnswer(EditText edt_title) {

        AnswerRetrofitService answerRetrofitService = CreateRetrofit.createRetrofit().create(AnswerRetrofitService.class);
        RegistAnswerRequest request = new RegistAnswerRequest("hello1", getInstance().getGroupId(), edt_title.getText().toString());
        Call<RegistAnswerResponse> call = answerRetrofitService.registAnswer(1l, request);

        call.enqueue(new Callback<RegistAnswerResponse>() {
            @Override
            public void onResponse(Call<RegistAnswerResponse> call, Response<RegistAnswerResponse> response) {
                if (response.isSuccessful()) {
                    RegistAnswerResponse body = response.body();
                    Log.d(TAG, "response 성공!!");

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

    public void takeAllAnswer(ListViewAdapter adapter){
        AnswerRetrofitService answerRetrofitService = CreateRetrofit.createRetrofit().create(AnswerRetrofitService.class);
        AllAnswerRequest request = new AllAnswerRequest(getInstance().getGroupId());
        Call<List<AllAnswerResponse>> call = answerRetrofitService.allAnswer(1l,request);

        call.enqueue(new Callback<List<AllAnswerResponse>>() {
            @Override
            public void onResponse(Call<List<AllAnswerResponse>> call, Response<List<AllAnswerResponse>> response) {
                if (response.isSuccessful()) {
                    List<AllAnswerResponse> body = response.body();
                    Log.d(TAG, "response 성공!!");
                    for (AllAnswerResponse allAnswerResponse : body) {
                        String answerContent = allAnswerResponse.getAnswerContent();
                        adapter.addItem(answerContent);

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

}

