package com.example.mave;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class List_insert extends AppCompatActivity {

    private final int GET_IMAGE = 200;
    ImageView List_insert_family_image;
    Context mcontext;
    EditText List_insert_addTitle, List_insert_addContent;
    Button List_insert_btn_UploadPicture, List_insert_btn_go;
    private final int GET_GALLERY_IMAGE = 200;
    Bitmap bm;
    private static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_insert);
        mcontext = this;

        List_insert_family_image = (ImageView) findViewById(R.id.list_insert_family_image);
        List_insert_addContent = (EditText) findViewById(R.id.list_insert_addContent);
        List_insert_addTitle = (EditText) findViewById(R.id.list_insert_addTitle);
        List_insert_btn_UploadPicture = (Button) findViewById(R.id.list_insert_btn_UploadPicture);


        List_insert_btn_go = (Button) findViewById(R.id.list_insert_btn_insert);
        List_insert_btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("Enroll_user_image",bitmapToByteArray(bm));
                intent.putExtra("addTitle", List_insert_addTitle.getText().toString());
                intent.putExtra("addContent", List_insert_addContent.getText().toString());

                setResult(RESULT_OK, intent);
                finish();
            }
        });
        List_insert_btn_UploadPicture.setOnClickListener(new View.OnClickListener() {
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
        List_insert_family_image.setImageBitmap(bm);
    }

    public byte[] bitmapToByteArray( Bitmap $bitmap ) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream() ;
        $bitmap.compress( Bitmap.CompressFormat.JPEG, 100, stream) ;
        byte[] byteArray = stream.toByteArray() ;
        return byteArray ;
    }

}
