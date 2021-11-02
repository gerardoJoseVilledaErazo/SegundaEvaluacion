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
        //String score = sharedPreferences.getString("SCORE", "");
        try {
            if ( enter.isEmpty() && att.isEmpty() ){
                Intent intent = new Intent(ScoreActivity.this, PlayActivity.class);
                startActivity(intent); // Inicia Activity
                Toast.makeText(ScoreActivity.this, "Play first.",Toast.LENGTH_SHORT).show();
                txtNumberScore.setText("Actual Score: 0");
                txtAccuScore.setText("Accumulated Score: 0");
            }else{
                //score();
                sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
                SharedPreferences.Editor editorConfig = sharedPreferences.edit();
                //String att = sharedPreferences.getString("ATT", "");
                Integer at = Integer.parseInt(att);
                at = at + 1;
                txtNumberScore.setText("Actual Score: " + at.toString());
                editorConfig.putString("SCORE", at.toString() );
                editorConfig.commit();
                /////////////////////////////////////////////////////////////////////////////// onResume y onpause
                //String accu = sharedPreferences.getString("SCORE", "");
                //Integer acc = Integer.parseInt(accu);
                //acumulador = acumulador + acc;
                ////////////////////////////////////////////////////////////////////////////// onpause
                //editorConfig.putString("ACUMULADOR", acumulador.toString() );//
                //editorConfig.commit();
                //acumulador = Integer.parseInt(sharedPreferences.getString("ACUMULADOR", ""));// este si va aqui
                //////////////////////////////////////////////////////////////////////////////
                //txtAccuScore.setText("Accumulated Score: " + acumulador.toString());
            }
        }catch (Exception e){}
    }
    /*
    private void score(){
        sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editorConfig = sharedPreferences.edit();
        //String att = sharedPreferences.getString("ATT", "");
        Integer at = Integer.parseInt(att);
        at = at + 1;
        txtNumberScore.setText("Actual Score: " + at.toString());
        editorConfig.putString("SCORE", at.toString() );
        /////////////////////////////////////////////////////////////////////////////// onResume y onpause
        String accu = sharedPreferences.getString("SCORE", "");
        Integer acc = Integer.parseInt(accu);
        acumulador = acumulador + acc;
        ////////////////////////////////////////////////////////////////////////////// onpause
        editorConfig.putString("ACUMULADOR", acumulador.toString() );//
        acumulador = Integer.parseInt(sharedPreferences.getString("ACUMULADOR", ""));// este si va aqui
        //////////////////////////////////////////////////////////////////////////////
        //txtAccuScore.setText("Accumulated Score: " + acumulador.toString());
        editorConfig.commit();
    }*/
    @Override
    protected void onResume() {
        super.onResume();
        String enter = sharedPreferences.getString("ENTER", "");
        String att = sharedPreferences.getString("ATT", "");
        //String score = sharedPreferences.getString("SCORE", "");
        try {
            if ( enter.isEmpty() && att.isEmpty() ){
                Intent intent = new Intent(ScoreActivity.this, PlayActivity.class);
                startActivity(intent); // Inicia Activity
                Toast.makeText(ScoreActivity.this, "Play first.",Toast.LENGTH_SHORT).show();
                txtNumberScore.setText("Actual Score: 0");
                txtAccuScore.setText("Accumulated Score: 0");
            }else{
                //OnResume
                sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
                String accu = sharedPreferences.getString("SCORE", "");
                Integer acc = Integer.parseInt(accu);
                acumulador = acumulador + acc;
///////////////////////////////////////////////////////////////////// score
        /*
        String att = sharedPreferences.getString("ATT", "");
        Integer at = Integer.parseInt(att);
        at = at + 1;
        txtNumberScore.setText("Actual Score: " + at.toString()); */
/////////////////////////////////////////////////////////////////////
                txtAccuScore.setText("Accumulated Score: " + acumulador.toString());
            }
        }catch (Exception e){}
    }
    @Override
    protected void onPause() {
        super.onPause();
        String enter = sharedPreferences.getString("ENTER", "");
        String att = sharedPreferences.getString("ATT", "");
        //String score = sharedPreferences.getString("SCORE", "");
        try {
            if ( enter.isEmpty() && att.isEmpty() ){
                Intent intent = new Intent(ScoreActivity.this, PlayActivity.class);
                startActivity(intent); // Inicia Activity
                Toast.makeText(ScoreActivity.this, "Play first.",Toast.LENGTH_SHORT).show();
                txtNumberScore.setText("Actual Score: 0");
                txtAccuScore.setText("Accumulated Score: 0");
            }else{
                //OnPause
                sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
                SharedPreferences.Editor editorConfig = sharedPreferences.edit();
                //String accu = sharedPreferences.getString("SCORE", "");
                //Integer acc = Integer.parseInt(accu);//
                //acumulador = acumulador + acc;
//////////////////////////////////////////////////////////////////////////////////////// score
                //String att = sharedPreferences.getString("ATT", "");
                Integer at = Integer.parseInt(att);
                at = at + 1;
//////////////////////////////////////////////////////////////////////////////////////// score
                editorConfig.putString("SCORE", at.toString() );
//////////////////////////////////////////////////////////////////////////////////////// score
                //editorConfig.putString("ACUMULADOR", acumulador.toString() );
                //acumulador = Integer.parseInt(sharedPreferences.getString("ACUMULADOR", ""));//
////////////////////////////////////////////////////////////////////////////////////////
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