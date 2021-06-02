package com.example.mave.Diary;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mave.R;
import com.example.mave.activities.MainActivity;

import java.util.Objects;

public class Level_Up_Dialog extends Dialog {
    private Context mContext;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_up_dialog);

        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button yes = findViewById(R.id.btn_level_up);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), MainActivity.class);
                getContext().startActivity(intent);
                dismiss();
            }
        });
    }

    public Level_Up_Dialog(Context context, Class<Level_Up_Dialog> level_up_dialogClass) {
        super(context);
        this.mContext = mContext;
    }
}

