package com.example.juego.utils;

import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static boolean verifyEditText(EditText editText) {
        if (editText.getText().toString().isEmpty()) {
            editText.setError("Este campo es requerido.");
            editText.requestFocus();
            return false;
        } else if (!verifyChars(editText)) {
            editText.setError("Solo se permite letras.");
            editText.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean verifyChars(EditText editText) {
        //Validamos solo caracteres Expresion regular
        Pattern ps = Pattern.compile("^[a-zA-Z ]+$");
        Matcher ms = ps.matcher(editText.getText().toString());
        boolean bs = ms.matches();
        return bs;
    }

    // Para Enter Number
    public static boolean verifyEditTextNumber(EditText editText ) {
        if (editText.getText().toString().isEmpty() ) {
            editText.setError("This field is required." );
            editText.requestFocus();
            return false;
        }
        return true;
    }
}
