package com.firebaseloginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.firebaseloginapp.AccountActivity.LoginActivity;
import com.firebaseloginapp.AccountActivity.SignupActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeProf extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private ContentHomeProf fragmentHome;
    private ContentNotificationProf fragmentNotification;
    private ContentSearchProf fragmentSearch;
    private MenuItem menuItem;

    //--------------------toolbar stuff-----------------
    private Toolbar toolbar;

    Button addContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_prof);


        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_navigation);

        fragmentHome = new ContentHomeProf();
        fragmentNotification = new ContentNotificationProf();
        fragmentSearch = new ContentSearchProf();

        setFragment(fragmentHome);


        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.nav_home:
                        //mMainNav.setItemBackgroundResource(R.color.colorGreen);
                        setFragment(fragmentHome);
                        return true;
                    case R.id.nav_search:
                        //mMainNav.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(fragmentSearch);
                        return true;
                    case R.id.nav_notification:
                        //mMainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(fragmentNotification);
                        return true;

                    default:
                        return false;
                }
            }
        });



        addContent = findViewById(R.id.addContent);

        addContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomeProf.this, pdfUploader.class);
                startActivityForResult(in, 1);
            }
        });







    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        if (id == R.id.disconnect) {
            Intent navLog = new Intent(HomeProf.this, LoginActivity.class);
            this.startActivity(navLog);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.header_menu, menu);
        return true;
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

}
