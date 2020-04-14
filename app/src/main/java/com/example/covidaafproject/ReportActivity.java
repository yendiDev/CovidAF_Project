package com.example.covidaafproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        setNavigation();
    }

    private void setNavigation(){
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.navigation_report);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.navigation_home:
                        Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(homeIntent);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.navigation_report:
                        return true;

                    case R.id.navigation_test:
                        Intent testIntent = new Intent(getApplicationContext(), TestActivity.class);
                        testIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(testIntent);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.navigation_more:
                        Intent moreIntent = new Intent(getApplicationContext(), MoreActivity.class);
                        moreIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(moreIntent);
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });
    }

}
