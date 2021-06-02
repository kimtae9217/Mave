package com.example.mave.PhotoBook;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mave.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;


public class List_item extends AppCompatActivity {

    ViewGroup viewGroup;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    RecyclerView recyclerView;
    MyRecyclerAdapter myRecyclerAdapter;
    List<ItemData> itemdata;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        mDatabase = FirebaseDatabase.getInstance(); // firebaseDatabase 인스턴스 생성
        mRef = mDatabase.getReference().child("mave"); // 생성된 database 를 참조하는 ref 생성
        mStorage = FirebaseStorage.getInstance(); // firebaseStorage 인스턴스 생성
        recyclerView = (RecyclerView) viewGroup.findViewById(R.id.photolist_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemdata = new ArrayList<ItemData>();
        myRecyclerAdapter = new MyRecyclerAdapter(this, itemdata);
        recyclerView.setAdapter(myRecyclerAdapter);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_page_1);

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
    }
}