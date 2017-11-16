package br.rv.buscacao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
    @BindView(R.id.progressBar_login)
    ProgressBar progressBar;
    Context contexto;
    Bitmap bitmap;
    @BindView(R.id.logar)
    Button btn_logar;
    @BindView(R.id.cadastrar_se)
    Button btn_cancelar;
    boolean loggedIn = false;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contexto = this;
        ButterKnife.bind(this);
        progressBar.setVisibility(View.GONE);

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);
        if (loggedIn) {
            Intent intent = new Intent(this, LogadoActivity.class); // ProfileActivity
            startActivity(intent);
        }
    }

    @OnClick(R.id.logar)
    public void logar() {
        btn_logar.setVisibility(View.INVISIBLE);
        btn_cancelar.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
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
                            SharedPreferences sharedPreferences = AppActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                            JsonParser jsonParser = new JsonParser();
                            JsonObject jo = (JsonObject) jsonParser.parse(response);
                            editor.putString(Config.TOKEN, jo.get("token").getAsString());
                            editor.putString(Config.EMAIL, jo.get("email").getAsString());
                            editor.putString(Config.USUARIO, jo.get("usuario").getAsString());
                            editor.putString(Config.ID, jo.get("id").getAsString());
                            editor.putString(Config.USER_NAME, jo.get("user_name").getAsString());

                            editor.commit();
                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(AppActivity.this, LogadoActivity.class);
                            startActivity(intent);
                            finish();

                            Toast.makeText(contexto, "Bem Vindo Ao Busca Cão", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(contexto, "Usuario ou Senha Incorreto", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                            btn_logar.setVisibility(View.VISIBLE);
                            btn_cancelar.setVisibility(View.VISIBLE);
                            Toast.makeText(contexto, "ERROR", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        btn_logar.setVisibility(View.VISIBLE);
                        btn_cancelar.setVisibility(View.VISIBLE);
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

