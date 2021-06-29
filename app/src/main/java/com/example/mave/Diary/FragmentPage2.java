package com.example.mave.Diary;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.mave.CreateRetrofit;
import com.example.mave.Dto.groupDto.FindGroupResponse;
import com.example.mave.Dto.groupDto.JoinGroupRequest;
import com.example.mave.PreferenceManager;
import com.example.mave.R;
import com.example.mave.repository.GroupRepository;
import com.example.mave.repository.MemberRepository;
import com.example.mave.service.GroupRetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.royrodriguez.transitionbutton.utils.WindowUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mave.Diary.Create_Diary.TAG;

public class FragmentPage2 extends Fragment {

    ViewGroup viewGroup;


    public static Boolean isJoined = false;
    public static Boolean isChanged = false;

    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2;
    private TextView DiaryName, diaryDate;
    private ImageView flower;
    final int[] flower_num = {R.drawable.state_1, R.drawable.state_2, R.drawable.state_3, R.drawable.state_4, R.drawable.state_5, R.drawable.state_6,R.drawable.state_7,R.drawable.state_8};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);
//        WindowUtils.makeStatusbarTransparent(getActivity());
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_page_2, container, false);
        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
        fab = (FloatingActionButton) viewGroup.findViewById(R.id.fab);
        fab1 = (FloatingActionButton) viewGroup.findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) viewGroup.findViewById(R.id.fab2);
        DiaryName = (TextView) viewGroup.findViewById(R.id.diarytitle);
        diaryDate = (TextView) viewGroup.findViewById(R.id.diaryDate);
        flower = (ImageView) viewGroup.findViewById(R.id.diary_flower);

        flower.setVisibility(View.INVISIBLE);


        findGroup();

        if (isJoined) {
            fab.setVisibility(View.GONE);
        }


        FloatingActionButton FloatingButton = (FloatingActionButton) viewGroup.findViewById(R.id.fab);
        FloatingButton.setOnClickListener(new OnClickListener() { //플로팅버튼 눌렀을 때 이벤트 (하위 버튼 띄우기)
            @Override
            public void onClick(View v) {


                anim();

                FloatingActionButton FloatingButton2 = (FloatingActionButton) viewGroup.findViewById(R.id.fab1); //플로팅버튼 눌렀을 때 이벤트(다이어리 만드는 버튼)


                FloatingButton2.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Create_Diary dialog = new Create_Diary(getContext());
                        // 커스텀 다이얼로그 배경 투명
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        dialog.setDialogListener(new Create_Diary.CustomDialogListener() {
                            @Override
                            public void onPositiveClicked(String diaryname) {
                                DiaryName.setText(diaryname);
                                diaryDate.setText(" D + 1 ");
                                flower.setVisibility(View.VISIBLE);
                                flower.setImageResource(flower_num[0]);
                            }

                            @Override
                            public void onNegativeClicked() {
                                //취소버튼 눌렀을 경우 구현될 코드 작성
                            }
                        });
                        dialog.show();
                        anim();
                    }
                });

                FloatingActionButton FloatingButton3 = (FloatingActionButton) viewGroup.findViewById(R.id.fab2); //플로팅버튼 눌렀을 때 이벤트(초대 버튼)
                FloatingButton3.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Join_Group dig_3 = new Join_Group(getActivity(), Join_Group.class);
                        // 커스텀 다이얼로그 배경 투명
                        dig_3.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dig_3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dig_3.show();
                        anim();
                    }
                });
            }
        });


        ImageButton button = (ImageButton) viewGroup.findViewById(R.id.diary);

        button.setOnClickListener(new OnClickListener() { // 다이어리를 눌렀을 때 이벤트
            @Override
            public void onClick(View v) {
                if (GroupRepository.getInstance().getGroupName() != null) {
                    Intent intent = new Intent(getActivity(), Page2_sub.class);
                    startActivity(intent);

                } else {
                    Not_Made_Diary_Dialog dig = new Not_Made_Diary_Dialog(getActivity(), Not_Made_Diary_Dialog.class);
                    // 커스텀 다이얼로그 배경 투명
                    dig.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dig.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dig.show();
                }
            }
        });

        return viewGroup;
    }


    private void findGroup() { // 다이어리 프래그먼트 띄울 때 내가 어떤 그룹에 가입되어 있는지를 확인하기 위한 API

        GroupRetrofitService groupRetrofitService = CreateRetrofit.createRetrofit().create(GroupRetrofitService.class);
        String userId = MemberRepository.getInstance().getUserId();
        JoinGroupRequest request = new JoinGroupRequest(userId);
        Call<FindGroupResponse> call = groupRetrofitService.findGroup(request);

        call.enqueue(new Callback<FindGroupResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<FindGroupResponse> call, Response<FindGroupResponse> response) {
                if (response.isSuccessful()) {
                    FindGroupResponse body = response.body();
                    Log.d(TAG, "response 성공!!");

                    if (!isChanged) {
                        GroupRepository.getInstance().setGroupId(body.getGroupId());
                        GroupRepository.getInstance().setGroupName(body.getGroupName());
                        GroupRepository.getInstance().setFlowerStatus(body.getFlowerStatus());
                        GroupRepository.getInstance().setDiaryDate(body.getDiaryDate());
                        GroupRepository.getInstance().setCompleteDate(body.getCompleteDate());

                        DiaryName.setText(GroupRepository.getInstance().getGroupName());
                        flower.setVisibility(View.VISIBLE);
                        flower.setImageResource(flower_num[GroupRepository.getInstance().getFlowerStatus()]);

                        LocalDateTime parse = LocalDateTime.parse(body.getQuestionTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                        LocalTime questionTime = LocalTime.of(parse.getHour(), parse.getMinute());
                        GroupRepository.getInstance().setQuestionTime(questionTime);

                        Log.d(TAG, "설정 시간은 !? - " + questionTime);
                        Log.d(TAG, "D-day는 !? - " + body.getDiaryDate());

                        diaryDate.setText(" D + " + body.getDiaryDate());

                        isJoined = true;

                    }
                    Log.d(TAG, "Day가 바뀌었나 !? - " + body.getDateChanged());

                    if (body.getDateChanged()) {
                        Page2_sub.isAlarmed = false;
                        Page2_sub.isCalled = false;
                    }


                } else {
                    Log.d(TAG, "response 실패 ㅠㅠ");
                    DiaryName.setText("그룹에 가입해주세요!");

                }
            }

            @Override
            public void onFailure(Call<FindGroupResponse> call, Throwable t) {
                Log.d(TAG, "onFailure => " + t.getMessage());
            }
        });
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void anim() {

        if (isFabOpen) {
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
        } else {
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);

            isFabOpen = true;
        }
    }


}
