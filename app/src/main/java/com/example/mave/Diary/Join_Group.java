package com.example.mave.Diary;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.mave.CreateRetrofit;
import com.example.mave.Dto.groupDto.JoinGroupRequest;
import com.example.mave.Dto.groupDto.JoinGroupResponse;
import com.example.mave.R;
import com.example.mave.activities.MainActivity;
import com.example.mave.repository.MemberRepository;
import com.example.mave.service.GroupRetrofitService;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mave.Diary.Create_Diary.TAG;

public class Join_Group extends Dialog implements View.OnClickListener {
    private Context mContext;



    private Button yes;
    private Button no;
    private EditText joinGroupId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_group);

        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        yes = findViewById(R.id.btnPositive);
        no = findViewById(R.id.btnNegative);
        joinGroupId = findViewById(R.id.edit_join_group);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }


    public Join_Group(Context context, Class<Join_Group> Join_Group_dialogClass) {
        super(context);
        this.mContext = mContext;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPositive: //확인 버튼을 눌렀을 때

                Long groupId = Long.parseLong(joinGroupId.getText().toString());
                joinGroup(groupId);

                Log.d(TAG, "그룹 가입 완료!! ");
                
                dismiss();
                break;
            case R.id.btnNegative: //취소 버튼을 눌렀을 때
                cancel();
                break;
        }




    }

    private void joinGroup(Long groupId) { // 그룹에 가입하는 API 서버에 저장된 그룹 ID값을 입력해야 함
        GroupRetrofitService groupRetrofitService = CreateRetrofit.createRetrofit().create(GroupRetrofitService.class);
        String userId = MemberRepository.getInstance().getUserId();
        JoinGroupRequest request = new JoinGroupRequest(userId);
        Call<JoinGroupResponse> call = groupRetrofitService.joinGroup(groupId, request);

        call.enqueue(new Callback<JoinGroupResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<JoinGroupResponse> call, Response<JoinGroupResponse> response) {
                if (response.isSuccessful()) {
                    JoinGroupResponse body = response.body();
                    Log.d(TAG, "response 성공!!");

                    Intent intent = new Intent(getContext(),MainActivity.class);
                    FragmentPage2.isJoined = true;
                    getContext().startActivity(intent);


                } else {
                    Log.d(TAG, "response 실패 ㅠㅠ");
                    Toast.makeText(getContext(),"찾으시는 그룹이 없습니다 ㅠㅠ",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<JoinGroupResponse> call, Throwable t) {
                Log.d(TAG, "onFailure => " + t.getMessage());
            }
        });
    }

}

