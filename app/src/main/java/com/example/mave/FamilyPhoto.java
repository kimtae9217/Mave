package com.example.mave;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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

        Title = (EditText)findViewById(R.id.addTitle);
        Content = (EditText)findViewById(R.id.addContent);

        btn_go = (Button)findViewById(R.id.btn_go);

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

