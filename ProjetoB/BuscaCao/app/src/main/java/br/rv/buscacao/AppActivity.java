package br.rv.buscacao;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mvc.imagepicker.ImagePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import br.rv.buscacao.util.Imagens;
import br.rv.buscacao.util.volley.FactorVolley;
import butterknife.BindBitmap;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppActivity extends AppCompatActivity {
    @BindView(R.id.cao_nome) EditText nome_cao;
    @BindView(R.id.image_view)   ImageView imageView;
    @BindView(R.id.image_stream_indicator )  TextView textView;
    Context contexto;
    Bitmap bitmap;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        contexto = this;
        ButterKnife.bind(this);
        imageView = (ImageView) findViewById(R.id.image_view);
        textView = (TextView) findViewById(R.id.image_stream_indicator);

        ImagePicker.setMinQuality(600, 600);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         bitmap = ImagePicker.getImageFromResult(this, requestCode, resultCode, data);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
        InputStream is = ImagePicker.getInputStreamFromResult(this, requestCode, resultCode, data);
        if (is != null) {
            textView.setText("Got input stream!");
            try {
                is.close();
            } catch (IOException ex) {
                // ignore
            }
        } else {
            textView.setText("Failed to get input stream!");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onPickImage(View view) {
        ImagePicker.pickImage(this, "Select your image:");
    }

    @OnClick(R.id.cao_enviar)
    public void buscar() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("nome",this.nome_cao.getText().toString());
        params.put("imagens", Imagens.encodeTobase64(bitmap));
        Log.i("essaqui","Testando");    
        String URL="http://10.10.143.244:4567/api/cao/save";
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Process the JSON
                        try{
//                            String cep = response.toString();

                            Toast.makeText(contexto, "Sucesso !", Toast.LENGTH_SHORT).show();

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(contexto, "Erro", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        FactorVolley.getInstance(this).addToRequestQueue(req);
    }
}
