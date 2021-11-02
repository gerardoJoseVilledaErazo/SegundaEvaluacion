package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin, btnStart, btnClue, btnScore;

    public static SharedPreferences sharedPreferences;
    public static String NAME_FILE = "configuration";
    int n = 10; //  n es el número hasta que quieres que llegue (1-10)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnScore = (Button) findViewById(R.id.btnScore);
        btnClue = (Button) findViewById(R.id.btnClue);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
        String user = sharedPreferences.getString("USER", "");

        SharedPreferences.Editor editorConfig = sharedPreferences.edit();
        Integer answerValue = (int) (Math.random() * n) + 1;
        editorConfig.putString("ANSWER", answerValue.toString() );
        editorConfig.commit();

        // Eventos
        btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( user.isEmpty()){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent); // Inicia Activity

                    Toast.makeText(MainActivity.this, "Login first.",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(MainActivity.this, PlayActivity.class));
                }
            }
        });
        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( user.isEmpty()){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent); // Inicia Activity
                    Toast.makeText(MainActivity.this, "Login first.",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(MainActivity.this, ScoreActivity.class));
                }
            }
        });
        btnClue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( user.isEmpty()){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent); // Inicia Activity
                    Toast.makeText(MainActivity.this, "Login first.",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(MainActivity.this, ClueActivity.class));
                }
            }
        });
    }
    //Ver datos
    public void ViewData(View view){
        startActivity(new Intent(MainActivity.this, MostrarActivity.class));
    }
    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(this, "OnStart", Toast.LENGTH_SHORT).show();
        // La actividad está a punto de hacerse visible.
    }
    // Fetch the stored data in onResume()
    // Because this is what will be called
    // when the app opens again
    @Override
    protected void onResume() {
        super.onResume();
    }
    // Store the data in the SharedPreference
    // in the onPause() method
    // When the user closes the application
    // onPause() will be called
    // and data will be stored
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
        //Toast.makeText(this, "OnStop", Toast.LENGTH_SHORT).show();
        // La actividad ya no es visible (ahora está "detenida")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this, "OnDestroy", Toast.LENGTH_SHORT).show();
        // La actividad está a punto de ser destruida.
    }
}