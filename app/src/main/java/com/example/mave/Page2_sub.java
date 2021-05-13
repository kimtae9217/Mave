package com.example.mave;

import android.content.Intent;
import android.os.Bundle; import androidx.annotation.Nullable; import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable; import androidx.appcompat.app.AppCompatActivity;

public class Page2_sub extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_page2_sub);

        TextView textview = (TextView)findViewById(R.id.todayQuestion); // 오늘의 질문 버튼

        textview.setOnClickListener(new View.OnClickListener() { // 오늘의 질문을 눌렀을 때 이벤트
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page2_sub.this, Page2_sub_answer.class);
                startActivity(intent);
            }
        });
    }
}

