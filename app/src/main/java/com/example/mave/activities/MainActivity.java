package com.example.mave.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.mave.BackPressCloseHandler;
import com.example.mave.PhotoBook.FragmentPage1;
import com.example.mave.Diary.FragmentPage2;
import com.example.mave.Settings.FragmentPage3;
import com.example.mave.R;
import com.example.mave.repository.GroupRepository;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.time.LocalTime;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ChipNavigationBar mBottomNV;
    FragmentManager fragmentManager;
    private BackPressCloseHandler backPressCloseHandler;

    private Fragment fragment;
    LocalTime questionTime;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        questionTime = GroupRepository.getInstance().getQuestionTime();


        Intent intent = getIntent();
        if(intent != null) {//푸시알림을 선택해서 실행한것이 아닌경우 예외처리
            String notificationData = intent.getStringExtra("test");
            if(notificationData != null)
                Log.d("FCM_TEST", notificationData);
        }

        backPressCloseHandler = new BackPressCloseHandler(this);
/*<======================================================================================================>*/
        if (savedInstanceState == null) {
            //mBottomNV.setItemSelected(R.id.content_layout, true);
            fragmentManager = getSupportFragmentManager();
            FragmentPage2 fragmentPage2 = new FragmentPage2();
            fragmentManager.beginTransaction().replace(R.id.content_layout, fragmentPage2).commit();
        }
        mBottomNV = findViewById(R.id.nav_view_chip);
        mBottomNV.setItemSelected(R.id.Diary, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, new FragmentPage2()).commit();
        mBottomNV.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.Album:
                        fragment=new FragmentPage1();
                        break;
                    case R.id.Diary:
                        fragment=new FragmentPage2();
                        break;
                    case R.id.Setting:
                        fragment=new FragmentPage3();
                        break;
                }
                if(fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, fragment).commit();
                } } });
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }
}