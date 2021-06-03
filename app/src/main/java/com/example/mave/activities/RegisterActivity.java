package com.example.mave.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mave.CreateRetrofit;
import com.example.mave.Dto.memeberDto.JoinMemberRequest;
import com.example.mave.Dto.memeberDto.JoinMemberResponse;
import com.example.mave.R;
import com.example.mave.service.MemberRetrofitService;
import com.royrodriguez.transitionbutton.utils.WindowUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class RegisterActivity extends AppCompatActivity {

    static final String TAG = "Mave";

    public Button registerBtn;
    public EditText userID, userName, userPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        WindowUtils.makeStatusbarTransparent(this);

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
                            Log.d(TAG, "response 성공!!");
                            Log.d(TAG, "회원가입 성공!!");
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            getApplicationContext().startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
                            startActivity(intent);
                            finish();


                        } else {
                            Log.d(TAG, "response 실패 ㅠㅠ");
                            Log.d(TAG, "회원가입 실패 ㅠㅠ");
                            Toast.makeText(RegisterActivity.this, "다시 시도해주세요!!!", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<JoinMemberResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure => " + t.getMessage());
                    }
                });

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}