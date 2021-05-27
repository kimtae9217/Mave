package com.example.mave.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mave.CreateRetrofit;
import com.example.mave.Dto.memeberDto.JoinMemberRequest;
import com.example.mave.Dto.memeberDto.JoinMemberResponse;
import com.example.mave.R;
import com.example.mave.repository.MemberRepository;
import com.example.mave.service.MemberRetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    static final String TAG = "Mave";

    public Button registerBtn;
    public EditText userID, userName, userPW;

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

                MemberRetrofitService memberRetrofitService = CreateRetrofit.createRetrofit().create(MemberRetrofitService.class);
                JoinMemberRequest request = new JoinMemberRequest(userID.getText().toString(), userName.getText().toString(), userPW.getText().toString());
                Call<JoinMemberResponse> call = memberRetrofitService.joinMember(request);

                call.enqueue(new Callback<JoinMemberResponse>() {
                    @Override
                    public void onResponse(Call<JoinMemberResponse> call, Response<JoinMemberResponse> response) {
                        if (response.isSuccessful()) {
                            JoinMemberResponse body = response.body();
                            Log.d(TAG,"response 성공!!");
                            MemberRepository instance = MemberRepository.getInstance();
                            instance.setUserId(userID.getText().toString());

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