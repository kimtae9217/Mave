package com.example.mave.PhotoBook;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.mave.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;


public class FragmentPage1 extends Fragment {


    final static int CODE = 1;
    ViewGroup viewGroup;

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    RecyclerView recyclerView;
    MyRecyclerAdapter myRecyclerAdapter;
    List<ItemData> itemdata;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_page_1, container, false);
        mDatabase = FirebaseDatabase.getInstance(); // firebaseDatabase 인스턴스 생성
        mRef = mDatabase.getReference().child("mave"); // 생성된 database 를 참조하는 ref 생성
        mStorage = FirebaseStorage.getInstance(); // firebaseStorage 인스턴스 생성
        recyclerView = (RecyclerView) viewGroup.findViewById(R.id.photolist_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        itemdata = new ArrayList<ItemData>();
        myRecyclerAdapter = new MyRecyclerAdapter(getActivity(),itemdata);
        recyclerView.setAdapter(myRecyclerAdapter);

//        Button button = (Button) viewGroup.findViewById(R.id.page1_btn_add_picture);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), List_insert.class);
//                startActivityForResult(intent, CODE);
//
//            }
//        });

        FloatingActionButton FloatingButton3 = (FloatingActionButton) viewGroup.findViewById(R.id.page1_btn_add_picture);
        FloatingButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), List_insert.class);
                startActivityForResult(intent, CODE);
            }
        });

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
                ItemData itemData = snapshot.getValue(ItemData.class);
                itemdata.add(itemData);
                myRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return viewGroup;
    }
}