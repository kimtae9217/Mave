package com.example.mave.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mave.CreateRetrofit;
import com.example.mave.Dto.memeberDto.LoginRequest;
import com.example.mave.Dto.memeberDto.LoginResponse;
import com.example.mave.R;
import com.example.mave.service.MemberRetrofitService;
import com.royrodriguez.transitionbutton.TransitionButton;
import com.royrodriguez.transitionbutton.utils.WindowUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mave.activities.RegisterActivity.TAG;

public class LoginActivity extends AppCompatActivity {

    private TransitionButton transitionLoginBtn;
    boolean isSuccessful;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        WindowUtils.makeStatusbarTransparent(this);
        getSupportActionBar().hide();

        EditText userID = findViewById(R.id.userID);
        EditText userPW = findViewById(R.id.userPW);
        transitionLoginBtn = findViewById(R.id.transition_button);
        TextView SignUp = findViewById(R.id.SignUp);

        transitionLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                transitionLoginBtn.startAnimation(); // 로그인 버튼 탭 눌렀을 때

                // Do your networking task or background work here.
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        /* ---------------------------Retrofit 통신--------------------------------------- */
                        MemberRetrofitService memberRetrofitService = CreateRetrofit.createRetrofit().create(MemberRetrofitService.class);
                        LoginRequest request = new LoginRequest(userID.getText().toString(), userPW.getText().toString());
                        Call<LoginResponse> call = memberRetrofitService.login(request);

                        call.enqueue(new Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                if (response.isSuccessful()) {
                                    LoginResponse body = response.body();
                                    Log.d(TAG, "response 성공!!");
                                    transitionLoginBtn.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND, new TransitionButton.OnAnimationStopEndListener() {
                                        @Override
                                        public void onAnimationStopEnd() {
                                            Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
//                                    isSuccessful = true;

                                } else {
                                    Log.d(TAG, "response 실패 ㅠㅠ");
                                    transitionLoginBtn.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
//                                    isSuccessful = false;
                                }
                            }

                            @Override
                            public void onFailure(Call<LoginResponse> call, Throwable t) {
                                Log.d(TAG, "onFailure => " + t.getMessage());
                                transitionLoginBtn.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
//                                isSuccessful = false;
                            }
                        });

//                        // Choose a stop animation if your call was succesful or not
//                        if (isSuccessful) {
//                            transitionLoginBtn.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND, new TransitionButton.OnAnimationStopEndListener() {
//                                @Override
//                                public void onAnimationStopEnd() {
//                                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                                    startActivity(intent);
//                                    finish();
//                                }
//                            });
//
//                        } else {
//                            transitionLoginBtn.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
//                        }
                    }
                }, 1000);
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