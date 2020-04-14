package com.example.covidaafproject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setNavigation();
    }

    private void setNavigation(){
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.navigation_home);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.navigation_home:
                        return true;

                    case R.id.navigation_report:
                        Intent reportIntent = new Intent(getApplicationContext(), ReportActivity.class);
                        reportIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(reportIntent);
                        overridePendingTransition(0, 0);
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
