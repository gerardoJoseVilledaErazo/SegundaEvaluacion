package com.example.juego;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.juego.utils.Utils;

public class LoginActivity extends AppCompatActivity {

    private Button btnLog;
    private EditText edtUserName, edtPassword;
    private CheckBox select;

    public static SharedPreferences sharedPreferences;
    public static String NAME_FILE = "configuration";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        edtUserName = (EditText) findViewById(R.id.edtUserName);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLog = (Button) findViewById(R.id.btnLog);
        select = (CheckBox) findViewById(R.id.cbCondiciones);

        btnLog.setOnClickListener( v -> {

            addUser();

            String user = sharedPreferences.getString("USER", "");
            Toast.makeText(
                    LoginActivity.this,
                    "Hello, "+user+"!",
                    Toast.LENGTH_SHORT
            ).show();
        });

    }

    private void addUser(){

            if(Utils.verifyEditText(edtUserName) && Utils.verifyEditText(edtPassword)) {

                if(!select.isChecked()){
                    String s = "Estado: " + (select.isChecked() ? "Terminos y condiciones aceptados" : "Acepte terminos y condiciones");
                    Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                    select.requestFocus();
                    return;
                }
                else{

                    // Fetching the stored data
                    // from the SharedPreference
                    sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);

                    SharedPreferences.Editor editorConfig = sharedPreferences.edit();
                    editorConfig.putString("USER", edtUserName.getText().toString());
                    editorConfig.putString("PASSWD", edtPassword.getText().toString());
                    editorConfig.commit();
                    Intent intent = new Intent(this, MainActivity.class);
                    //Inicia Activity
                    startActivity(intent);
                }
            }

    }

    // Fetch the stored data in onResume()
    // Because this is what will be called
    // when the app opens again
    @Override
    protected void onResume() {
        super.onResume();

        // Fetching the stored data
        // from the SharedPreference
                                                                                                        //SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);

        String user = sharedPreferences.getString("USER", "");
        String passwd = sharedPreferences.getString("PASSWD", "");
        //int a = sh.getInt("age", 0);

        // Setting the fetched data
        // in the EditTexts
        edtUserName.setText(user);
        edtPassword.setText(passwd);
                                                                                                        //age.setText(String.valueOf(a));
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
        // with a file name "MySharedPref"
        // in private mode
        //SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        //SharedPreferences.Editor myEdit = sharedPreferences.edit();

        sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editorConfig = sharedPreferences.edit();

        // write all the data entered by the user in SharedPreference and apply
        //myEdit.putString("name", name.getText().toString());
        //myEdit.putInt("age", Integer.parseInt(age.getText().toString()));
        //myEdit.apply();

        editorConfig.putString("USER", edtUserName.getText().toString());
        editorConfig.putString("PASSWD", edtPassword.getText().toString());
        editorConfig.apply();
    }

    //Al dar clic en el botón “Cancelar”
    // debe borrar el texto o valor de todos los componentes del formulario.
    public void Cancel(View view) {
        edtUserName.setText("");
        edtPassword.setText("");
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



            /*
            if(Utils.verifyEditText(edtUserName) && Utils.verifyEditText(edtPassword)) {

                if(!select.isChecked()){
                    String s = "Estado: " + (select.isChecked() ? "Terminos y condiciones aceptados" : "Acepte terminos y condiciones");
                    Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                    select.requestFocus();
                    return;
                }
                else{
                    Intent intent = new Intent(this, MainActivity.class);
                    String username = edtUserName.getText().toString();
                    String password = edtPassword.getText().toString();

                    Bundle extras = new Bundle();
                    extras.putString("USERNAME",username);
                    extras.putString("PASSWORD",password);
                    //Agrega el objeto bundle a el Intent
                    intent.putExtras(extras);
                    //Inicia Activity
                    startActivity(intent);

                    Toast.makeText(LoginActivity.this,
                            "Login as: " + username,
                            Toast.LENGTH_SHORT).show();
                }
            }*/



/*
        sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);

        SharedPreferences.Editor editorConfig = sharedPreferences.edit();
        editorConfig.putString("USER", edtUserName.getText().toString());
        editorConfig.putString("PASSWD", edtPassword.getText().toString());
        editorConfig.commit();*/