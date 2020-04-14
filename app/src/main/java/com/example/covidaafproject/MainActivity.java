package com.example.covidaafproject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import zone.nora.coronavirus.Coronavirus;
import zone.nora.coronavirus.data.latest.Latest;
import zone.nora.coronavirus.data.locations.Locations;
import zone.nora.coronavirus.data.locations.location.Location;

public class MainActivity extends AppCompatActivity {

    private Spinner countries_spinner;

    private String user_choice;

    private TextView countries_confirmed, countries_recovered, countries_death;

    private ProgressDialog dialog;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        countries_confirmed = findViewById(R.id.main_countries_confirmed);
        countries_recovered = findViewById(R.id.main_countries_recovered);
        countries_death = findViewById(R.id.main_countries_death);

        setNavigation();
        setSpinner();

    }

    private void setNavigation() {
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.navigation_home);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

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

    private void setSpinner() {
        countries_spinner = findViewById(R.id.main_spinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countries_spinner.setAdapter(adapter);

        countries_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dialog = new ProgressDialog(MainActivity.this);
                dialog.setMessage("Loading data...");
                dialog.setCancelable(false);
                dialog.show();

                user_choice = (String) parent.getItemAtPosition(position);
                new DataAsyncTask().execute(user_choice);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public class DataAsyncTask extends AsyncTask<String, String, Latest> {


        @Override
        protected Latest doInBackground(String... strings) {

            // Create an instance of the API.
            Coronavirus coronavirus = new Coronavirus();

            String choice = strings[0];

            Latest latest = null;
            try {
                Locations location = coronavirus.getLocationsByCountry(choice);
                latest = location.getLatest();
                return latest;

            } catch (IOException e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Latest latest) {
            super.onPostExecute(latest);

            if (latest == null) {
                //set the data
                countries_confirmed.setText("---");
                countries_recovered.setText("---");
                countries_death.setText("---");
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Data not available. Try another country", Toast.LENGTH_SHORT).show();


            } else {
                int confirmed = latest.getConfirmed();
                int recovered = latest.getRecovered();
                int deaths = latest.getDeaths();

                //set the data
                countries_confirmed.setText(String.valueOf(confirmed));
                countries_recovered.setText(String.valueOf(recovered));
                countries_death.setText(String.valueOf(deaths));
                dialog.dismiss();
            }
        }
    }
}
