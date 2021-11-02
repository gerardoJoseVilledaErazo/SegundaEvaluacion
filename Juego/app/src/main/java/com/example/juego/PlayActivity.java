package com.example.juego;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juego.utils.Utils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class PlayActivity extends AppCompatActivity {

    TextView txtUser, txtAttempts, txtAnswer;
    TextInputEditText edtEnter;
    MaterialButton btnDone;
    public static SharedPreferences sharedPreferences;
    public static String NAME_FILE = "configuration";
    Integer attempts = 10;
    boolean esIgual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        txtUser = (TextView) findViewById(R.id.txtUser);
        txtAttempts = (TextView) findViewById(R.id.txtAttempts);
        txtAnswer = (TextView) findViewById(R.id.txtAnswer);
        edtEnter = (TextInputEditText) findViewById(R.id.edtEnter);
        btnDone = (MaterialButton) findViewById(R.id.btnDone);

        txtAttempts.setText("Number of attempts allowed: " + attempts);

        sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
        String user = sharedPreferences.getString("USER", "");

        if ( user == null){
            Intent intent = new Intent(PlayActivity.this, LoginActivity.class);
            startActivity(intent); // Inicia Activity
            Toast.makeText(PlayActivity.this, "Login first.",Toast.LENGTH_SHORT).show();

        }else{
            txtUser.setText("User: " + user);
        }

        // Done Button
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                done();
            }
        });
    }

    private void done(){
        if(!isValid(edtEnter.getText() ) ){
            edtEnter.setError("Required");
        }else{
            if(Utils.verifyEditTextNumber(edtEnter) ){
                // Fetching the stored data
                // from the SharedPreference
                sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
                SharedPreferences.Editor editorConfig = sharedPreferences.edit();
                editorConfig.putString("ENTER", edtEnter.getText().toString() );
                editorConfig.commit();

                String enter = sharedPreferences.getString("ENTER", "");
                String answer = sharedPreferences.getString("ANSWER", "");

                Integer in = Integer.parseInt(enter);
                Integer ans = Integer.parseInt(answer);

                //esIgual = (in == ans);

                if ( enter.equals(answer)  ){
                    Intent intent = new Intent(PlayActivity.this, MainActivity.class);
                    startActivity(intent); // Inicia Activity
                    String user = sharedPreferences.getString("USER", "");
                    Toast.makeText(
                            PlayActivity.this,
                            "Congratulations " + user+", you have won!",
                            Toast.LENGTH_SHORT
                    ).show();
                }else{
                    edtEnter.setText("");
                    if(in < ans){
                        Toast.makeText(
                                PlayActivity.this,
                                "The correct number is greater",
                                Toast.LENGTH_SHORT
                        ).show();
                    }else if(in > ans){
                        Toast.makeText(
                                PlayActivity.this,
                                "The correct number is less",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
                attempts = attempts - 1;
                txtAttempts.setText("Number of attempts allowed: " + attempts);

                editorConfig.putString("ATT", attempts.toString() );
                editorConfig.commit();
            }

        }
    }

    private boolean isValid(@Nullable Editable text) {
        return text != null;
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
        editorConfig.putString("ENTER", edtEnter.getText().toString() );
        editorConfig.putString("ATT", attempts.toString() );
        editorConfig.apply();
    }
}