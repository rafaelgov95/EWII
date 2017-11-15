package br.rv.buscacao.view.login;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.json.JSONException;
import org.json.JSONObject;

import br.rv.buscacao.R;
import br.rv.buscacao.config.Config;
import br.rv.buscacao.utils.date.DatePickerFragmentNasc;
import br.rv.buscacao.utils.date.DatePickerFragmentNascUser;
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
        cadastrar_data_nascimento.setFocusable(false);
        AwesomeValidation mAwesomeValidation = new AwesomeValidation(ValidationStyle.UNDERLABEL);
        mAwesomeValidation.setContext(this);  // mandatory for UNDERLABEL style
//        AwesomeValidation mAwesomeValidation = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);
        mAwesomeValidation.addValidation(this, R.id.user_cadastar_email, Patterns.EMAIL_ADDRESS, R.string.err_email);
//        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
//        mAwesomeValidation.addValidation(this, R.id.edt_password, regexPassword, R.string.err_password);
        mAwesomeValidation.addValidation(this, R.id.user_cadastrar_pwd1, R.id.user_cadastrar_pwd2, R.string.err_senha_confirmacao);
//        mAwesomeValidation.validate();
//        mAwesomeValidation.setContext(this);


    }
    @OnClick(R.id.user_cadastar_data_nascimento)
    public void DataNasc(View v) {
        DatePickerFragmentNascUser newFragment = new DatePickerFragmentNascUser();
        newFragment.show(getFragmentManager(),"DataPicker2");
    }
    @OnClick(R.id.user_cadastrar_cadastrar)
    public void cadastrar() {
      ;
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

//    private void addValidationForEditText(Activity activity) {
//        mAwesomeValidation.addValidation(activity, R.id.edt_userid, "[a-zA-Z0-9_-]+", R.string.err_userid);
//        mAwesomeValidation.addValidation(activity, R.id.edt_password, "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}", R.string.err_password);
//        mAwesomeValidation.addValidation(activity, R.id.edt_password_confirmation, R.id.edt_password, R.string.err_password_confirmation);
//        mAwesomeValidation.addValidation(activity, R.id.edt_firstname, "[a-zA-Z\\s]+", R.string.err_name);
//        mAwesomeValidation.addValidation(activity, R.id.edt_lastname, "[a-zA-Z\\s]+", R.string.err_name);
//        mAwesomeValidation.addValidation(activity, R.id.edt_email, Patterns.EMAIL_ADDRESS, R.string.err_email);
//        mAwesomeValidation.addValidation(activity, R.id.edt_ip, Patterns.IP_ADDRESS, R.string.err_ip);
//        mAwesomeValidation.addValidation(activity, R.id.edt_tel, RegexTemplate.TELEPHONE, R.string.err_tel);
//        mAwesomeValidation.addValidation(activity, R.id.edt_zipcode, "\\d+", R.string.err_zipcode);
//        mAwesomeValidation.addValidation(activity, R.id.edt_year, Range.closed(1900, Calendar.getInstance().get(Calendar.YEAR)), R.string.err_year);
//        mAwesomeValidation.addValidation(activity, R.id.edt_height, Range.closed(0.0f, 2.72f), R.string.err_height);
//        setValidationButtons();
//    }


    @OnClick(R.id.user_cadastrar_cancelar)
    public void cancelar() {
        finish() ;
    }
}

