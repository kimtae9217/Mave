package com.example.mave.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mave.R;
import com.example.mave.models.ApiResponse;
import com.example.mave.retrofitUtil.ApiClient;
import com.example.mave.retrofitUtil.ApiInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.userID) EditText user_ID;
    @BindView(R.id.userPW) EditText user_PW;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loginUser(View view) {
        Call<ApiResponse> apiResponseCall = apiInterface.loginUser(user_ID.getText().toString(), user_PW.getText().toString());

        apiResponseCall.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.body() != null){
                    ApiResponse apiresponse = response.body();

                    if(apiresponse.isSuccess())
                    {
                        Toast.makeText(LoginActivity.this, "로그인 성공!",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "유저 없음!" + apiresponse.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "에러입니다",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void registerUser(View view){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}