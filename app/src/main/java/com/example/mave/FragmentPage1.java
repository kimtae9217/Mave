package com.example.mave;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
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

public class FragmentPage1 extends Fragment implements MyRecyclerAdapter.MyRecyclerViewClickListener {

    private RecyclerView recyclerView;
    private MyRecyclerAdapter adapter_1;


    ArrayList<ItemData> dataList = new ArrayList<>();
    MyRecyclerAdapter adapter = new MyRecyclerAdapter(dataList);
    static int i=0;
    int[] cat = {R.drawable.family, R.drawable.family, R.drawable.family,
            R.drawable.family, R.drawable.family, R.drawable.family,
            R.drawable.family, R.drawable.family, R.drawable.family,
            R.drawable.family};
    private View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_page_1, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(this);

        Button button = (Button)view.findViewById(R.id.btn_insert);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View innerView = getLayoutInflater().inflate(R.layout.list_insert, null);
                final Dialog mDialog = new Dialog(getContext());

                mDialog.setTitle("Title");
                mDialog.setContentView(innerView);
                mDialog.setCancelable(true);

                WindowManager.LayoutParams params = mDialog.getWindow().getAttributes();
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                mDialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

                final EditText editTitle = mDialog.findViewById(R.id.addTitle);
                final EditText editCont = mDialog.findViewById(R.id.addContent);
                Button btn_go = mDialog.findViewById(R.id.btn_go);

                String myTitle = editTitle.getText().toString();

                Toast.makeText(getContext(), myTitle, Toast.LENGTH_SHORT).show();
                mDialog.dismiss();
            }
        });
        /*mDialog.show();*/
        adapter.notifyDataSetChanged();

        return view;
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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onItemClicked(int position) {
        Toast.makeText(getContext(), ""+(position+1), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClicked(int position) {
        adapter.remove(position);
        Toast.makeText(getContext(),"리스트 삭제", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTitleClicked(int position) {
        Toast.makeText(getContext(),dataList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onContentClicked(int position) {
        Toast.makeText(getContext(),dataList.get(position).getContent(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onImageViewClicked(int position) {
        Toast.makeText(getContext(), ""+(position+1), Toast.LENGTH_SHORT).show();
    }
   /* public void onMenuInsert(View view) {

        final View innerView = getLayoutInflater().inflate(R.layout.list_insert, null);
        final Dialog mDialog = new Dialog(getContext());
        mDialog.setTitle("Title");
        mDialog.setContentView(innerView);
        mDialog.setCancelable(true);

        WindowManager.LayoutParams params = mDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mDialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

        final EditText editTitle = mDialog.findViewById(R.id.addTitle);
        final EditText editCont = mDialog.findViewById(R.id.addContent);
        Button btn_go = mDialog.findViewById(R.id.btn_go);

        //입력버튼을 누르면 실행되는 이벤트
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myTitle = editTitle.getText().toString();
                String myCont = editCont.getText().toString();

                dataList.add(new ItemData(cat[i], myTitle, myCont));
                Toast.makeText(getContext(), myTitle, Toast.LENGTH_SHORT).show();
                mDialog.dismiss();
                    *//*Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                    startActivityForResult(intent, PICK_FROM_ALBUM);
                mImageCaptureUri = data.getData();

                Log.d("SmartWheel",mImageCaptureUri.getPath().toString());*//*
            }
        });

        mDialog.show();
        adapter.notifyDataSetChanged();
    }*/

}
