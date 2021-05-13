package com.example.mave;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.example.mave.FragmentPage1.CODE;

public class FragmentPage2 extends Fragment {

    ViewGroup viewGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_page_2, container, false);


        ImageButton button = (ImageButton)viewGroup.findViewById(R.id.flower);

        button.setOnClickListener(new View.OnClickListener() { // 다이어리를 눌렀을 때 이벤트
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Page2_sub.class);
                startActivity(intent);
            }
        });

        return viewGroup;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public void clickBtn(View view) {
        Intent intent = new Intent(getActivity(), Page2_sub_answer.class);
        startActivity(intent);
    }
}

