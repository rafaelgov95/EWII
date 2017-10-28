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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mvc.imagepicker.ImagePicker;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import br.rv.buscacao.util.Imagens;
import br.rv.buscacao.util.volley.FactorVolley;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppActivity extends AppCompatActivity {
    @BindView(R.id.login_email) EditText login_email;
    @BindView(R.id.login_password ) EditText login_password;

    Context contexto;
    Bitmap bitmap;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        contexto = this;
        ButterKnife.bind(this);
    }
    @OnClick(R.id.logar)
    public void logar() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email","rafael@gmail.com");
        params.put("password", "12345");
        JSONObject a = new JSONObject(params);

        String URL="http://192.168.43.165:4567/login";

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            Toast.makeText(contexto, "Sucesso !", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                        public void onErrorResponse(VolleyError error){
                        Log.i("essaqui",error.toString());                    }
                }
        );
        FactorVolley.getInstance(this).addToRequestQueue(req);
    }
}
