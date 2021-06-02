package com.example.mave.PhotoBook;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mave.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class List_insert extends AppCompatActivity {

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    ImageButton selectImage;
    EditText edtTitle, edtContent;
    Button insertBtn;
    private static final int Gallery_Code = 1;
    Uri imageUrl = null;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_insert);

        selectImage = findViewById(R.id.selectImage);
        edtTitle = findViewById(R.id.addTitle);
        edtContent = findViewById(R.id.addContent);
        insertBtn = findViewById(R.id.insertBtn);

        mDatabase = FirebaseDatabase.getInstance(); // firebaseDatabase 인스턴스 생성
        mRef = mDatabase.getReference().child("mave"); // 생성된 database 를 참조하는 ref 생성
        mStorage = FirebaseStorage.getInstance(); // firebaseStorage 인스턴스 생성
        progressDialog = new ProgressDialog(this);

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, Gallery_Code);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Gallery_Code && resultCode == RESULT_OK) {
            imageUrl = data.getData();
            selectImage.setImageURI(imageUrl);
        }
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fn = edtTitle.getText().toString().trim();
                final String ln = edtContent.getText().toString().trim();

                if (!(fn.isEmpty() && ln.isEmpty() && imageUrl != null)){
                    progressDialog.setTitle("업로딩중..");
                    progressDialog.show();

                    StorageReference filepath = mStorage.getReference().child("이미지 post").child(imageUrl.getLastPathSegment());
                    filepath.putFile(imageUrl).addOnSuccessListener(taskSnapshot -> {
                        Task<Uri> downloadUrl =taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(task -> {
                            String t = task.getResult().toString();

                            DatabaseReference newPost = mRef.push();

                            newPost.child("Title").setValue(fn);
                            newPost.child("Content").setValue(ln);
                            newPost.child("image").setValue(task.getResult().toString());

                            progressDialog.dismiss();
                        });
                    });
                }
            }
        });
    }
}