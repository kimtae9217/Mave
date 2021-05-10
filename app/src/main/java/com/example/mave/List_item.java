package com.example.mave;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class List_item extends AppCompatActivity {
    FragmentPage1 fragment_page_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_insert);

        Intent intent = getIntent();
        String title = intent.getStringExtra("addTitle");
        String content = intent.getStringExtra("addContent");

        fragment_page_1 = new FragmentPage1();
        getSupportFragmentManager().beginTransaction().replace(R.id.list_item, fragment_page_1).commit();

        Bundle bundle = new Bundle();
        bundle.putString("addTitle", title);
        bundle.putString("addContent", content);

        fragment_page_1.setArguments(bundle);
    }
}
