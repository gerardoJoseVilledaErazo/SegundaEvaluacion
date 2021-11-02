package com.example.juego;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class ClueActivity extends AppCompatActivity {

    TextView txtAnswer;
    public static SharedPreferences sharedPreferences;
    public static String NAME_FILE = "configuration";
    int n = 10; //  n es el número hasta que quieres que llegue (1-10)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clue);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        txtAnswer = (TextView) findViewById(R.id.txtAnswer);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    // Fetch the stored data in onResume()
    // Because this is what will be called
    // when the app opens again
    @Override
    protected void onResume() {
        super.onResume();

        // Fetching the stored data
        // from the SharedPreference
        sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
        String answer = sharedPreferences.getString("ANSWER", "");

        // Setting the fetched data
        // in the EditTexts
        txtAnswer.setText(answer);
    }

    // Store the data in the SharedPreference
    // in the onPause() method
    // When the user closes the application
    // onPause() will be called
    // and data will be stored
    @Override
    protected void onPause() {
        super.onPause();
        // Creating a shared pref object
        // with a file name "configuration"
        // in private mode
        sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editorConfig = sharedPreferences.edit();

        // write all the data entered by the user in SharedPreference and apply
        editorConfig.apply();
    }
}