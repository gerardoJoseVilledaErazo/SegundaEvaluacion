package com.example.juego;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreActivity extends AppCompatActivity {

    TextView txtUserScore, txtNumberScore, txtAccuScore;
    public static SharedPreferences sharedPreferences;
    public static String NAME_FILE = "configuration";
    Integer acumulador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        txtUserScore = (TextView) findViewById(R.id.txtUserScore);
        txtNumberScore = (TextView) findViewById(R.id.txtNumberScore);
        txtAccuScore = (TextView) findViewById(R.id.txtAccuScore);
        sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
        String user = sharedPreferences.getString("USER", "");
        txtUserScore.setText("User: " + user);
        String enter = sharedPreferences.getString("ENTER", "");
        String att = sharedPreferences.getString("ATT", "");
        try {
            if ( enter.isEmpty() && att.isEmpty() ){
                Intent intent = new Intent(ScoreActivity.this, PlayActivity.class);
                startActivity(intent); // Inicia Activity
                Toast.makeText(ScoreActivity.this, "Play first.",Toast.LENGTH_SHORT).show();
                txtNumberScore.setText("Actual Score: 0");
                txtAccuScore.setText("Accumulated Score: 0");
            }else{
                sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
                SharedPreferences.Editor editorConfig = sharedPreferences.edit();
                Integer at = Integer.parseInt(att);
                at = at + 1;
                acumulador = acumulador + at;
                txtNumberScore.setText("Actual Score: " + at.toString());
                txtAccuScore.setText("Accumulated Score: " + acumulador.toString());
                editorConfig.commit();
            }
        }catch (Exception e){}
    }
    @Override
    protected void onResume() {
        super.onResume();
        String enter = sharedPreferences.getString("ENTER", "");
        String att = sharedPreferences.getString("ATT", "");
        try {
            if ( enter.isEmpty() && att.isEmpty() ){
                Intent intent = new Intent(ScoreActivity.this, PlayActivity.class);
                startActivity(intent); // Inicia Activity
                txtNumberScore.setText("Actual Score: 0");
                txtAccuScore.setText("Accumulated Score: 0");
            }else{
                //OnResume
                sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
                Integer at = Integer.parseInt(att);
                acumulador = Integer.parseInt(sharedPreferences.getString("ACUMULADOR", ""));

                if (acumulador == null){
                    txtAccuScore.setText("Accumulated Score: " + at.toString());
                }else{
                    acumulador = acumulador + at + 1;
                    txtAccuScore.setText("Accumulated Score: " + acumulador.toString());
                }

            }
        }catch (Exception e){}
    }
    @Override
    protected void onPause() {
        super.onPause();
        String enter = sharedPreferences.getString("ENTER", "");
        String att = sharedPreferences.getString("ATT", "");
        try {
            if ( enter.isEmpty() && att.isEmpty() ){
                Intent intent = new Intent(ScoreActivity.this, PlayActivity.class);
                startActivity(intent); // Inicia Activity
                txtNumberScore.setText("Actual Score: 0");
                txtAccuScore.setText("Accumulated Score: 0");
            }else{
                //OnPause
                sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
                SharedPreferences.Editor editorConfig = sharedPreferences.edit();
                editorConfig.putString("ACUMULADOR", acumulador.toString() );
                editorConfig.apply();
            }
        }catch (Exception e){}
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
}