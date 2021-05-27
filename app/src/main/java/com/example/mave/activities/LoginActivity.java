package com.example.mave.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mave.CreateRetrofit;
import com.example.mave.Dto.memeberDto.LoginRequest;
import com.example.mave.Dto.memeberDto.LoginResponse;
import com.example.mave.R;
import com.example.mave.service.MemberRetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mave.activities.RegisterActivity.TAG;

public class LoginActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText userID = findViewById(R.id.userID);
        EditText userPW = findViewById(R.id.userPW);
        Button loginBtn = findViewById(R.id.loginBtn);
        TextView SignUp = findViewById(R.id.SignUp);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MemberRetrofitService memberRetrofitService = CreateRetrofit.createRetrofit().create(MemberRetrofitService.class);
                LoginRequest request = new LoginRequest(userID.getText().toString(), userPW.getText().toString());
                Call<LoginResponse> call = memberRetrofitService.login(request);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            LoginResponse body = response.body();
                            Log.d(TAG, "response 성공!!");

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Log.d(TAG, "response 실패 ㅠㅠ");
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure => " + t.getMessage());
                    }
                });

            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
        });

    }
}
