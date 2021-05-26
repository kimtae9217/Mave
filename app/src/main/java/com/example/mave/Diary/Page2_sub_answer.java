package com.example.mave.Diary;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mave.PreferenceManager;
import com.example.mave.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Page2_sub_answer extends AppCompatActivity {

    private ListView listView;
    private Button btn_add, btn_custom;
    private EditText edt_title;
    private ListViewAdapter adapter;
    static int count = 0;
    private ImageView flower;
    private Context mContext;
    SharedPreferences pref;
    SharedPreferences.Editor editor;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_page2_sub_answer);

        edt_title = (EditText) findViewById(R.id.edt_answer);
        btn_add = (Button) findViewById(R.id.btn_answer);
        listView = (ListView) findViewById(R.id.listMemo);
        flower = (ImageView) findViewById(R.id.diary_flower);
        //btn_custom = (Button) findViewById(R.id.customquestion);
        mContext = this;

        adapter = new ListViewAdapter(Page2_sub_answer.this);
        listView.setAdapter(adapter);

        // 데이터 추가하기
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.addItem(edt_title.getText().toString());
                edt_title.setText("");
                count++;
                /*SharedPreferences sharedPreferences = getSharedPreferences("ansewercount",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("addcount", count);
                editor.commit();*/
                PreferenceManager.setInt(mContext, "test", count);
                adapter.notifyDataSetChanged();
            }
        });
        FloatingActionButton FloatingButton = (FloatingActionButton)findViewById(R.id.customquestion);
        FloatingButton.setOnClickListener(new View.OnClickListener() { //플로팅버튼 눌렀을 때 이벤트
            @Override
            public void onClick(View v) {
                Create_Question dia = new Create_Question(Page2_sub_answer.this);
                // 커스텀 다이얼로그 배경 투명
                //dia.requestWindowFeature(Window.FEATURE_NO_TITLE);
                //dia.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dia.setDialogListener(new Create_Question.CustomDialogListener() {
                    @Override
                    public void onPositiveClicked(String custom_question) {
                        Toast.makeText(getApplication(), custom_question, Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNegativeClicked() {
                        //취소버튼 눌렀을 경우 구현될 코드 작성
                    }
                });
                dia.show();
            }
        });
    }
}

