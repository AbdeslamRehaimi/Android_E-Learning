package com.firebaseloginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.firebaseloginapp.AccountActivity.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class homeEtudiant extends AppCompatActivity {


    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private contentHomeEtudiant fragmentHome;
    private contentNotificationEtudiant fragmentNotification;
    private contentSearchEtudiant fragmentSearch;
    private MenuItem menuItem;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_etudiant);


        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_navigation);

        fragmentHome = new contentHomeEtudiant();
        fragmentNotification = new contentNotificationEtudiant();
        fragmentSearch = new contentSearchEtudiant();

        setFragment(fragmentHome);

        //--------------------toolbar stuff--------------------
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        if (id == R.id.disconnect) {
            Intent navLog = new Intent(homeEtudiant.this, LoginActivity.class);
            this.startActivity(navLog);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
