package com.example.mave.Diary;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mave.R;

public class Create_Question extends Dialog implements View.OnClickListener {

    private Button positiveButton;
    private Button negativeButton;
    private EditText editName;
    private Context context;
    private CustomDialogListener customDialogListener;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public Create_Question(Context context) {
        super(context);
        this.context = context;
    }


    interface CustomDialogListener{
        void onPositiveClicked(String custom_question);
        void onNegativeClicked();
    }
    public void setDialogListener(CustomDialogListener customDialogListener){
        this.customDialogListener = customDialogListener;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_question);

//init
        positiveButton = (Button)findViewById(R.id.question_btnPositive);
        negativeButton = (Button)findViewById(R.id.question_btnNegative);
        editName = (EditText)findViewById(R.id.editquestion);

        //버튼 클릭 리스너 등록
        positiveButton.setOnClickListener(this);
        negativeButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.question_btnPositive: //확인 버튼을 눌렀을 때
                //각각의 변수에 EidtText에서 가져온 값을 저장
                String Custom_Question = editName.getText().toString();
                //인터페이스의 함수를 호출하여 변수에 저장된 값들을 Activity로 전달
                customDialogListener.onPositiveClicked(Custom_Question);
                dismiss();
                break;
            case R.id.question_btnNegative: //취소 버튼을 눌렀을 때
                cancel();
                break;
        }

    }
}