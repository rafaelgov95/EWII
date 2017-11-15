package br.rv.buscacao.utils.imagen;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by rafael on 19/10/17.
 */

public class Imagens {
    public static String encodeTobase64(Bitmap image)
    {
        Bitmap immagex=image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String a  = Base64.encodeToString(b,Base64.DEFAULT);
        return  "data:image/png;base64,".concat(a);

    }
    public static Bitmap decodeBase64(String input)
    {
        String teste[] = input.split("data:image/png;base6");
        byte[] decodedByte = Base64.decode(teste[1], 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

}
