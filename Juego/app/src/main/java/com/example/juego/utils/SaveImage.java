package com.example.juego.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveImage extends AsyncTask<Bitmap, Void, Boolean> {

    private String path;
    private String name;
    private Context context;

    public SaveImage(Context context, String path, String name) {
        this.context = context;
        this.path = path;
        this.name = name;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context, "Guardando, por favor espere...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        Toast.makeText(context, "IMG: " + name + "guardada en : " + path, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Boolean doInBackground(Bitmap... bitmaps) {
        Boolean saved = false;
        for (Bitmap tmp: bitmaps) {
            saved = saveToInternalStorage(tmp, path, name);
        }
        return saved;
    }

    private Boolean saveToInternalStorage(Bitmap bitMapImage, String path, String name){
        Boolean saved = false;
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/" + path);
        directory.mkdirs();

        FileOutputStream fileOutputStream = null;
        try{
            fileOutputStream = new FileOutputStream(new File(directory, name));
            bitMapImage.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
                saved = true;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return saved;
    }
}
