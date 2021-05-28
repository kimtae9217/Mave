package com.example.mave.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.mave.BackPressCloseHandler;
import com.example.mave.PhotoBook.FragmentPage1;
import com.example.mave.Diary.FragmentPage2;
import com.example.mave.Settings.FragmentPage3;
import com.example.mave.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ChipNavigationBar mBottomNV;
    FragmentManager fragmentManager;
    private BackPressCloseHandler backPressCloseHandler;

    private Fragment fragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        backPressCloseHandler = new BackPressCloseHandler(this);
/*<======================================================================================================>*/
        if (savedInstanceState == null) {
            //mBottomNV.setItemSelected(R.id.content_layout, true);
            fragmentManager = getSupportFragmentManager();
            FragmentPage1 fragmentPage1 = new FragmentPage1();
            fragmentManager.beginTransaction().replace(R.id.content_layout, fragmentPage1).commit();
        }
        mBottomNV = findViewById(R.id.nav_view_chip);
        mBottomNV.setItemSelected(R.id.Album, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, new FragmentPage1()).commit();
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

/*setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        backPressCloseHandler = new BackPressCloseHandler(this);

        mBottomNV = findViewById(R.id.nav_view);
        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { //NavigationItemSelecte
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                BottomNavigate(menuItem.getItemId());


                return true;
            }
        });
        mBottomNV.setSelectedItemId(R.id.navigation_1);
        }
        private void  BottomNavigate(int id) {
        String tag = String.valueOf(id);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
            if (currentFragment != null) {
                fragmentTransaction.hide(currentFragment);
            }

            Fragment fragment = fragmentManager.findFragmentByTag(tag);
            if (fragment == null) {
                if (id == R.id.navigation_1) {
                    fragment = new FragmentPage1();
                } else if (id == R.id.navigation_2){
                    fragment = new FragmentPage2();
                }else {
                    fragment = new FragmentPage3();
                }
                fragmentTransaction.add(R.id.content_layout, fragment, tag);
            } else {
                fragmentTransaction.show(fragment);
            }
            fragmentTransaction.setPrimaryNavigationFragment(fragment);
            fragmentTransaction.setReorderingAllowed(true);
            fragmentTransaction.commitNow();
        }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }*/

 /*if (savedInstanceState == null) {
            mBottomNV.setItemSelected(R.id.content_layout, true);
            fragmentManager = getSupportFragmentManager();
            FragmentPage1 fragmentPage1 = new FragmentPage1();
            fragmentManager.beginTransaction().replace(R.id.content_layout, fragmentPage1).commit();
        }

        mBottomNV.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch (id) {
                    case R.id.navigation_1:
                        fragment = new FragmentPage1();
                        break;
                    case R.id.navigation_2:
                        fragment = new FragmentPage2();
                        break;
                    case R.id.navigation_3:
                        fragment = new FragmentPage3();
                        break;
                }
                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_layout, fragment).commit();
                } else {
                    Log.e(TAG, "Error in creating fragment");
                }
            }
        });*/

/*chipNavigationBar = findViewById(R.id.nav_view_chip);
        chipNavigationBar.setItemSelected(R.id.Album, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, new AlbumFragment()).commit();

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.Album:
                        fragment=new AlbumFragment();
                        break;
                    case R.id.Diary:
                        fragment=new DiaryFragment();
                        break;
                    case R.id.Setting:
                        fragment=new SettingFragment();
                        break;
                }

                if(fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, fragment).commit();
                }
            }
        });*/