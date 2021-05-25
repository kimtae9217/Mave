package com.example.mave;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.content.Context.MODE_PRIVATE;
import static com.example.mave.FragmentPage1.CODE;
import static com.example.mave.Page2_sub_answer.count;

public class FragmentPage2 extends Fragment {

    ViewGroup viewGroup;
    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2;
    private TextView DiaryName;
    private ImageView flower;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    final int[] flower_num = {R.drawable.state_0, R.drawable.state_1, R.drawable.state_2, R.drawable.state_3, R.drawable.yellowflower};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_page_2, container, false);
        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
        fab = (FloatingActionButton) viewGroup.findViewById(R.id.fab);
        fab1 = (FloatingActionButton) viewGroup.findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) viewGroup.findViewById(R.id.fab2);
        DiaryName = (TextView) viewGroup.findViewById(R.id.diarytitle);
        flower = (ImageView) viewGroup.findViewById(R.id.diary_flower);
        SharedPreferences sf = this.getActivity().getSharedPreferences("flowercount",MODE_PRIVATE);

        FloatingActionButton FloatingButton = (FloatingActionButton)viewGroup.findViewById(R.id.fab);
        FloatingButton.setOnClickListener(new OnClickListener() { //플로팅버튼 눌렀을 때 이벤트 (하위 버튼 띄우기)
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "하위 버튼 띄우기", Toast.LENGTH_SHORT).show();
                anim();

                FloatingActionButton FloatingButton2 = (FloatingActionButton)viewGroup.findViewById(R.id.fab1); //플로팅버튼 눌렀을 때 이벤트(다이어리 만드는 버튼)
                FloatingButton2.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Create_Diary dialog = new Create_Diary(getContext());
                        dialog.setDialogListener(new Create_Diary.CustomDialogListener() {
                            @Override
                            public void onPositiveClicked(String diaryname) {
                                DiaryName.setText(diaryname);
                                flower.setImageResource(flower_num[4]);

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

                FloatingActionButton FloatingButton3 = (FloatingActionButton)viewGroup.findViewById(R.id.fab2); //플로팅버튼 눌렀을 때 이벤트(초대 버튼)
                FloatingButton3.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "다이어리에 초대", Toast.LENGTH_SHORT).show();
                        anim();
                    }
                });
            }
        });

        ImageButton button = (ImageButton)viewGroup.findViewById(R.id.flower);
        button.setOnClickListener(new OnClickListener() { // 다이어리를 눌렀을 때 이벤트
            @Override
            public void onClick(View v) {
                if (!flower.equals(R.drawable.yellowflower)) {
                    Not_Made_Diary_Dialog dig = new Not_Made_Diary_Dialog(getActivity(), Not_Made_Diary_Dialog.class);
                    dig.show();
                } else {
                    Intent intent = new Intent(getActivity(), Page2_sub.class);
                    startActivity(intent);
                }
            }
        });

        //int level = sf.getInt("ansewercount", 0);
        int level = PreferenceManager.getInt(getContext(), "test");
        if (level == 0) {
            flower.setImageResource(R.drawable.state_0);
        }
        else if(level == 1) {
            flower.setImageResource(R.drawable.state_1);
        }
        else if(level == 2) {
            flower.setImageResource(R.drawable.state_2);
        }
        else if(level > 3) {
            flower.setImageResource(R.drawable.state_3);
        }
        return viewGroup;
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

