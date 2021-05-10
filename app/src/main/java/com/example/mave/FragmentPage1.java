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
    private GridLayoutManager gridLayoutManager;
    ViewGroup viewGroup;
    Bitmap bm;
    ImageView familypicture;
    TextView txt_content, txt_title;

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

        /*familypicture = (ImageView) viewGroup.findViewById(R.id.familypicture);*/


        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_page_1,container,false);
        txt_content = (TextView)viewGroup.findViewById(R.id.txt_content);
        txt_title = (TextView)viewGroup.findViewById(R.id.txt_title);
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
/*
        Bundle bundle = getArguments();
        String content = bundle.getString("addContent");
        String title = bundle.getString("addTitle");
        txt_content.setText(content);
        txt_title.setText(title);*/
        /*Bundle bundle = getArguments();
        String txt_content_fragment1 = bundle.getString("addContent");
        String txt_title_fragment1 = bundle.getString("addTitle");*/

        return viewGroup;
    }

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        *//*switch (requestCode) {
            case CODE:
                ItemData itemdata = new ItemData(R.drawable.family,"그룹 이름", "그룹 인원","그룹 내용");
                itemdata.setContent(data.getStringExtra("addContent"));
                itemdata.setTitle(data.getStringExtra("addTitle"));
                *//**//*PreferenceManager.setString(getContext(),"title",data.getStringExtra("addTitle"));
                PreferenceManager.setString(getContext(),"content",data.getStringExtra("addContent"));*//**//*
                arrayList.add(itemdata);
                recycleAdapter.notifyDataSetChanged();
        }*//*
        *//*InputStream is = null;
        try {
            is = getActivity().getContentResolver().openInputStream(data.getData());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bm = BitmapFactory.decodeStream(is);
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        familypicture.setImageBitmap(bm);*//*
        txt_title.setText(data.getStringExtra("addTitle"));
        txt_content.setText(data.getStringExtra("addContent"));
    }*/
    /*public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
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