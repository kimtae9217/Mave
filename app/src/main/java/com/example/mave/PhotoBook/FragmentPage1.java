package com.example.mave.PhotoBook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        recyclerView = (RecyclerView) viewGroup.findViewById(R.id.photolist);
        Page1_content = (TextView) viewGroup.findViewById(R.id.list_item_content);
        Page1_title = (TextView) viewGroup.findViewById(R.id.list_item_title);
        Page1_family_picture = (ImageView) viewGroup.findViewById(R.id.list_insert_family_image);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        arrayList = new ArrayList<>();
        recycleAdapter = new MyRecyclerAdapter(arrayList);
        recyclerView.setAdapter(recycleAdapter);

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
                ItemData itemData = new ItemData(R.id.list_insert_family_image, "제목", "내용");
                PreferenceManager.setString(getContext(), "Enroll_user_image", data.getStringExtra("Image"));
                PreferenceManager.setString(getContext(), "addTitle", data.getStringExtra("Title"));
                PreferenceManager.setString(getContext(), "addContent", data.getStringExtra("Content"));
                bm = byteArrayToBitmap(data.getByteArrayExtra("Enroll_user_image"));
                Page1_family_picture.setImageBitmap(bm);
                itemData.setFamilyphoto((data.getStringExtra("Enroll_user_image")));
                itemData.setTitle(data.getStringExtra("addTitle"));
                itemData.setContent(data.getStringExtra("addContent"));
                arrayList.add(itemData);
                recycleAdapter.notifyDataSetChanged();
                break;
        }
    }
    public Bitmap byteArrayToBitmap(byte[] $byteArray ) {
        Bitmap bitmap = BitmapFactory.decodeByteArray( $byteArray, 0, $byteArray.length ) ;
        return bitmap ;
    }

}
