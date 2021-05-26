package com.example.mave.PhotoBook;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mave.PreferenceManager;
import com.example.mave.R;

public class List_item extends AppCompatActivity {
    GridLayout container;
    TextView List_item_title, List_item_content;
    ImageView List_item_family_picture;
    Context mcontext;
    Bitmap bm;
    private static final int REQUEST_CODE = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_page_1);
        container = (GridLayout) findViewById(R.id.photolist);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item, container, true);

        mcontext = this;

        List_item_title = (TextView)findViewById(R.id.list_item_title);
        List_item_content = (TextView)findViewById(R.id.list_item_content);
        List_item_family_picture = (ImageView) findViewById(R.id.list_item_familypicture);
        bm = StringToBitmap(getIntent().getStringExtra("Enroll_user_image"));
        List_item_family_picture.setImageBitmap(bm);
        List_item_title.setText(""+ PreferenceManager.getString(mcontext,"addTitle"));
        List_item_content.setText(""+PreferenceManager.getString(mcontext,"addContent"));
    }
    public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}