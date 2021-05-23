package com.example.mave;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FamilyPhoto extends AppCompatActivity {

    EditText Content,Title;
    RadioGroup rb;
    RadioButton radio;
    Button btn_go;
    Context mcontext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_insert);
        mcontext = this;

        Title = (EditText)findViewById(R.id.list_insert_addTitle);
        Content = (EditText)findViewById(R.id.list_insert_addContent);

        btn_go = (Button)findViewById(R.id.list_insert_btn_insert);

        //데이터를 입력받는다.
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("Title",Title.getText().toString());
                intent.putExtra("Content",Content.getText().toString());


                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }

}

