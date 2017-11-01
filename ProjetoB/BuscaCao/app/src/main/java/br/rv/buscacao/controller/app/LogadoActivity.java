package br.rv.buscacao.controller.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import br.rv.buscacao.AppActivity;
import br.rv.buscacao.R;
import br.rv.buscacao.config.Config;
import br.rv.buscacao.util.volley.FactorVolley;
import br.rv.buscacao.util.volley.GsonPostRequest;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogadoActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logado);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.logout)
    public void deslogar() {
        SharedPreferences sharedPreferences = LogadoActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        // Creating editor to store values to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Adding values to editor
        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);
        editor.putString(Config.TOKEN, "ERRO");
        // Saving values to editor
        editor.commit();
        // Starting profile activity
        finish();

    }
}

