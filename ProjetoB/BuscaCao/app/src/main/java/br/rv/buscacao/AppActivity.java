package br.rv.buscacao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import br.rv.buscacao.config.Config;
import br.rv.buscacao.utils.volley.FactorVolley;
import br.rv.buscacao.view.logado.LogadoActivity;
import br.rv.buscacao.view.login.CadastrarActivity;
import br.rv.buscacao.utils.volley.GsonPostRequest;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppActivity extends AppCompatActivity {
    @BindView(R.id.login_email)
    EditText login_email;
    @BindView(R.id.login_password)
    EditText login_password;

    Context contexto;
    Bitmap bitmap;

    boolean loggedIn= false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contexto = this;
        ButterKnife.bind(this);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);
        if (loggedIn) {
            Intent intent = new Intent(this,LogadoActivity.class); // ProfileActivity
            startActivity(intent);
        }
    }

    @OnClick(R.id.logar)
    public void logar() {

        final String URL = Config.login;
        JSONObject map = new JSONObject();
        try {
            map.put("email", login_email.getText().toString());
            map.put("password", login_password.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String requestBody = map.toString();
        GsonPostRequest<String> req = new GsonPostRequest<String>(Request.Method.POST, URL, String.class, requestBody,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (!response.equalsIgnoreCase("ERRO")) {
//                            // Creating a SharedPreference
                            SharedPreferences sharedPreferences = AppActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                            // Creating editor to store values to SharedPreferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            // Adding values to editor
                            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);

                            editor.putString(Config.TOKEN,response);
//                             Saving values to editor
                            editor.commit();
//                             Starting profile activity
                            Intent intent = new Intent(AppActivity.this, LogadoActivity.class); // ProfileActivity
                            startActivity(intent);
                            finish();
                            Toast.makeText(contexto, "Bem Vindo Ao Busca Cão"   , Toast.LENGTH_LONG).show();
                        } else {
                            // If the server response is not success
                            // Displaying an error message on toast
                            Toast.makeText(contexto, "Usuario ou Senha Incorreto", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(contexto, "ERROR", Toast.LENGTH_LONG).show();

                    }
                });
        FactorVolley.getInstance(this).addToRequestQueue(req);
    }

    @OnClick(R.id.cadastrar_se)
    public void cadastrar_se() {
        Intent cadastrarActivity = new Intent(getApplicationContext(), CadastrarActivity.class);
        startActivity(cadastrarActivity);
    }
}

