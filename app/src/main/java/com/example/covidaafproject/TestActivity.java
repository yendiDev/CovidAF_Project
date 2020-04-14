package com.example.covidaafproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        setNavigation();
    }

    private void setNavigation(){
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.navigation_test);
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
                        Intent reportIntent = new Intent(getApplicationContext(), ReportActivity.class);
                        reportIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(reportIntent);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.navigation_test:
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
