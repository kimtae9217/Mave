package com.example.mave.Diary;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mave.R;

import java.util.Objects;


public class Not_Made_Diary_Dialog extends Dialog {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.not_made_diary_dialog);

        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button yes = findViewById(R.id.btndiaryyes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public Not_Made_Diary_Dialog(Context context, Class<Not_Made_Diary_Dialog> not_made_diary_dialogClass) {
        super(context);
        this.mContext = mContext;
    }
}
