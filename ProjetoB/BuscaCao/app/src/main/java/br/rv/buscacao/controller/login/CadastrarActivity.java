package br.rv.buscacao.controller.login;

import android.content.Context;
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

import br.rv.buscacao.R;
import br.rv.buscacao.config.Config;
import br.rv.buscacao.utils.volley.FactorVolley;
import br.rv.buscacao.utils.volley.GsonPostRequest;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastrarActivity extends AppCompatActivity {

    @BindView(R.id.fom_cadastro_cao_nome)
    EditText cadastrar_nome;
    @BindView(R.id.user_cadastar_sobrenome)
    EditText cadastrar_sobre_nome;
    @BindView(R.id.user_cadastar_data_nascimento)
    EditText cadastrar_data_nascimento;
    @BindView(R.id.user_cadastar_email)
    EditText cadastrar_email;
    @BindView(R.id.user_cadastrar_pwd1)
    EditText cadastrar_pwd1;
    @BindView(R.id.user_cadastrar_pwd2)
    EditText cadastrar_pwd2;
Context context;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cadastro);
        context=this;
        ButterKnife.bind(this);

    }
    @OnClick(R.id.user_cadastrar_cadastrar)
    public void cadastrar() {
        if (cadastrar_pwd1.getText().toString().equals(cadastrar_pwd2.getText().toString())) {
            final String URL = Config.cadastrar;
            JSONObject map = new JSONObject();
            try {
                map.put("user_nome", cadastrar_nome.getText().toString());
                map.put("nome", cadastrar_email.getText().toString());
                map.put("sobre_nome", cadastrar_sobre_nome.getText().toString());
                map.put("data", cadastrar_data_nascimento.getText().toString());
                map.put("email", cadastrar_email.getText().toString());
                map.put("password", cadastrar_pwd1.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            final String requestBody = map.toString();
            GsonPostRequest<String> req = new GsonPostRequest<String>(Request.Method.POST, URL, String.class, requestBody,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("Criado", response);
                            Toast.makeText(context, "Usuario Criado com Sucesso", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, "Erro ao criar", Toast.LENGTH_LONG).show();

                        }
                    });
            FactorVolley.getInstance(this).addToRequestQueue(req);
        }

    }



    @OnClick(R.id.user_cadastrar_cancelar)
    public void cancelar() {
        finish() ;
    }
}

