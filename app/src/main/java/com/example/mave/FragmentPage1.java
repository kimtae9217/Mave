package com.example.mave;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.preference.PreferenceManager.*;
import static com.example.mave.PreferenceManager.setString;

public class FragmentPage1 extends Fragment {


    final static int CODE = 1;
    private ArrayList<ItemData> arrayList;
    private MyRecyclerAdapter recycleAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    ViewGroup viewGroup;
    TextView txt_content, txt_title;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_page_1, container, false);
        recyclerView = (RecyclerView) viewGroup.findViewById(R.id.photolist);
        txt_content = (TextView) viewGroup.findViewById(R.id.txt_content);
        txt_title = (TextView) viewGroup.findViewById(R.id.txt_title);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        arrayList = new ArrayList<>();
        recycleAdapter = new MyRecyclerAdapter(arrayList);
        recyclerView.setAdapter(recycleAdapter);

        Button button = (Button) viewGroup.findViewById(R.id.btn_insert);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), List_insert.class);
                startActivityForResult(intent, CODE);
            }
        });
        return viewGroup;
    }

    /*public void startActivityForResult(Intent intent, int requestCode, @Nullable Intent data) {
        switch (requestCode) {
            case CODE:
                ItemData itemData = new ItemData(R.drawable.family,"제목", "내용");
                *//*itemData.setFamilyphoto(data.getStringExtra("groupName"));*//*
                itemData.setTitle(data.getStringExtra("groupContents"));
                itemData.setContent(data.getStringExtra("groupNumbers"));
                setString(getContext(),"rebuild",data.getStringExtra("groupName"));
                setString(getContext(),"number",data.getStringExtra("groupNumbers"));
                arrayList.add(itemData);
                recycleAdapter.notifyDataSetChanged();

        }
    }*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case CODE:
                ItemData itemData = new ItemData(R.drawable.family, "제목", "내용");
                /*itemData.setFamilyphoto(data.getStringExtra("groupName"));*/
                itemData.setTitle(data.getStringExtra("addTitle"));
                itemData.setContent(data.getStringExtra("addContent"));
                setString(getContext(), "title", data.getStringExtra("Title"));
                setString(getContext(), "content", data.getStringExtra("Content"));
                arrayList.add(itemData);
                recycleAdapter.notifyDataSetChanged();

        }
    }
}

