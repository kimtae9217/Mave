package com.example.mave;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
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

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentPage1 extends Fragment {

    /*private RecyclerView recyclerView;
    private MyRecyclerAdapter adapter_1;
    private ArrayList<ItemData> items = new ArrayList<>();
    ArrayList<ItemData> dataList = new ArrayList<>();
    private View view;*/
    final static int CODE = 1;
    private ArrayList<ItemData> arrayList;
    private MyRecyclerAdapter recycleAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    ViewGroup viewGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        /*View view = inflater.inflate(R.layout.fragment_page_1, container, false);
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(context, items);
        recyclerView.setAdapter(adapter);*/
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_page_1,container,false);
        recyclerView = (RecyclerView) viewGroup.findViewById(R.id.photolist);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        arrayList = new ArrayList<>();
        recycleAdapter = new MyRecyclerAdapter(arrayList);
        recyclerView.setAdapter(recycleAdapter);

        Button button = (Button)viewGroup.findViewById(R.id.btn_insert);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), List_insert.class);
                startActivity(intent);
            }
        });

        return viewGroup;
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case CODE:
                ItemData itemdata = new ItemData(R.drawable.family,"그룹 이름", "그룹 인원","그룹 내용");
                itemdata.setContent(data.getStringExtra("groupName"));
                itemdata.setTitle(data.getStringExtra("groupContents"));
                PreferenceManager.setString(getContext(),"rebuild",data.getStringExtra("addTitle"));
                PreferenceManager.setString(getContext(),"number",data.getStringExtra("addContent"));
                arrayList.add(itemdata);
                recycleAdapter.notifyDataSetChanged();

        }
    }*/
}


/*final View innerView = getLayoutInflater().inflate(R.layout.list_insert, null);
        final Dialog mDialog = new Dialog(getContext());
        mDialog.setTitle("Title");
        mDialog.setContentView(innerView);
        mDialog.setCancelable(true);

        Button button = (Button)view.findViewById(R.id.btn_insert);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WindowManager.LayoutParams params = mDialog.getWindow().getAttributes();
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                mDialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

                final EditText editTitle = mDialog.findViewById(R.id.addTitle);
                final EditText editCont = mDialog.findViewById(R.id.addContent);
                Button btn_go = mDialog.findViewById(R.id.btn_go);

                String myTitle = editTitle.getText().toString();
                String myCont = editCont.getText().toString();

                dataList.add(new ItemData(cat[i], myTitle, myCont));
                Toast.makeText(getContext(), myTitle, Toast.LENGTH_SHORT).show();
                mDialog.dismiss();
            }
        });
        mDialog.show();
        adapter.notifyDataSetChanged();

        return view;*/