package com.example.mave;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static java.security.AccessController.getContext;

public class List_insert extends AppCompatActivity {

    private final int GET_IMAGE = 200;
    ImageView userimage;
    Context mcontext;
    EditText addTitle, addContent;
    Button btn_UploadPicture, btn_go;
    private final int GET_GALLERY_IMAGE = 200;
    Bitmap bm;
    private static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_insert);
        mcontext = this;

        /*userimage = (ImageView) findViewById(R.id.user_image);*/
        addContent = (EditText) findViewById(R.id.addContent);
        addTitle = (EditText) findViewById(R.id.addTitle);
        btn_UploadPicture = (Button) findViewById(R.id.btn_UploadPicture);

        btn_go = (Button) findViewById(R.id.btn_go);
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                /*intent.putExtra("userimage",BitmapToString(bm));*/
                intent.putExtra("addTitle", addTitle.getText().toString());
                intent.putExtra("addContent", addContent.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        /*btn_UploadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doTakeAlbumAction();
            }
        });
    }

    public void doTakeAlbumAction() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        InputStream is = null;
        try {
            is = getContentResolver().openInputStream(data.getData());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bm = BitmapFactory.decodeStream(is);
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        userimage.setImageBitmap(bm);
    }

    public static String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos);
        byte[] bytes = baos.toByteArray();
        String temp = Base64.encodeToString(bytes, Base64.DEFAULT);
        return temp;
    }*/
    }
}