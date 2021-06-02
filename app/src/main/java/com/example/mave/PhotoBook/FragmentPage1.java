package com.example.mave.PhotoBook;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mave.PreferenceManager;
import com.example.mave.R;

public class FragmentPage1 extends Fragment {


    final static int CODE = 1;
    private ArrayList<ItemData> arrayList;
    private MyRecyclerAdapter recycleAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    ViewGroup viewGroup;
    TextView Page1_content, Page1_title;
    ImageView Page1_family_picture;
    String Image;
    Bitmap bm;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_page_1, container, false);

        Button button = (Button) viewGroup.findViewById(R.id.page1_btn_add_picture);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), List_insert.class);
                startActivityForResult(intent, CODE);
            }
        });

        return viewGroup;
    }
}