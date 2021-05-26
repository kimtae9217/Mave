package com.example.mave.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mave.Dto.JoinMemberRequest;
import com.example.mave.Dto.JoinMemberResponse;
import com.example.mave.R;
import com.example.mave.service.MemberRetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    public Button registerBtn;
    public EditText userID, userName, userPW;
    static final String TAG = "Mave";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerBtn = findViewById(R.id.registerBtn);
        userID = findViewById(R.id.userID);
        userName = findViewById(R.id.userName);
        userPW = findViewById(R.id.userPW);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.211.1:8080/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                MemberRetrofitService memberJoinService = retrofit.create(MemberRetrofitService.class);
                JoinMemberRequest request = new JoinMemberRequest(userID.getText().toString(), userName.getText().toString(), userPW.getText().toString());
                Call<JoinMemberResponse> call = memberJoinService.joinMember(request);

                call.enqueue(new Callback<JoinMemberResponse>() {
                    @Override
                    public void onResponse(Call<JoinMemberResponse> call, Response<JoinMemberResponse> response) {
                        if (response.isSuccessful()) {
                            JoinMemberResponse body = response.body();
                            Log.d(TAG,"response 성공!!");
//                            textTest.setText(body.getUserId().toString());
                        }else{
                            Log.d(TAG,"response 실패 ㅠㅠ");

                        }
                    }

                    @Override
                    public void onFailure(Call<JoinMemberResponse> call, Throwable t) {
                        Log.d(TAG,"onFailure => " + t.getMessage());
                    }
                });

            }
        });
    }
}