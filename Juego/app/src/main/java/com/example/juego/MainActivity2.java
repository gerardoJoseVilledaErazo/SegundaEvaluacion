package com.example.juego;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.juego.utils.SaveImage;

import java.io.FileDescriptor;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {

    private static final int PICK_PHOTO_FOR_AVATAR = 1;
    ImageView imgView;
    Bitmap img;

    final int READ_EXTERNAL_STORAGE_PERMISSION_CODE = 23;
    final int WRITE_EXTERNAL_STORAGE_PERMISSION_CODE = 23;

    ActivityResultLauncher<Intent> activityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent data = result.getData();
                    if( result.getResultCode() == Activity.RESULT_OK) {
                        Uri selectedImage = data.getData();
                        Bitmap bmp = null;
                        try{
                            bmp = getBitMapFromURI(selectedImage);
                            if ( bmp != null ) {
                                img = bmp;
                                imgView.setImageBitmap(bmp);
                            }

                        } catch (IOException ioException) {
                            Toast.makeText(MainActivity2.this, "Error al cargar la imagen", Toast.LENGTH_SHORT).show();
                            ioException.printStackTrace();
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imgView = findViewById(R.id.imageView);
        img = ((BitmapDrawable) imgView.getDrawable()).getBitmap();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_abrir:
                openFile();
                break;
            case R.id.menu_guardar:
                saveFile();
                break;
            case R.id.menu_cancelar:
                cancelar();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openFile() {
        Toast.makeText(this, "Menú abrir", Toast.LENGTH_SHORT).show();
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            // preguntar por los permisos
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_PERMISSION_CODE);
            }
        }
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activityResult.launch(intent);
        //startActivityForResult(intent,PICK_PHOTO_FOR_AVATAR);
    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bmp = null;
            try{
                bmp = getBitMapFromURI(selectedImage);
                if ( bmp != null ) {
                    img = bmp;
                    imgView.setImageBitmap(bmp);
                }

            } catch (IOException ioException) {
                Toast.makeText(this, "Error al cargar la imagen", Toast.LENGTH_SHORT).show();
                ioException.printStackTrace();
            }
        }
    }*/

    // Metodo que permite obtener el URI de la imagen
    private Bitmap getBitMapFromURI(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(uri,"r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    // método que permite guardar un archivo
    private void saveFile() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            // Pedir permiso

            // M de Marshmellow//////////////////////////////////////////////////////////////
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_EXTERNAL_STORAGE_PERMISSION_CODE);
            }
        } else {
            // Si ya tiene permisos
            Bitmap bitmap = ((BitmapDrawable) imgView.getDrawable()).getBitmap();

            String name = "IMG" + String.format("%d.png", System.currentTimeMillis());
            new SaveImage(this, "My Folder", name).execute(bitmap);
        }
    }

    private void cancelar() {
        Toast.makeText(this, "Menu Cancelar", Toast.LENGTH_SHORT).show();
        imgView.setImageBitmap(img);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_EXTERNAL_STORAGE_PERMISSION_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Ya fue concedido
        }
    }
}