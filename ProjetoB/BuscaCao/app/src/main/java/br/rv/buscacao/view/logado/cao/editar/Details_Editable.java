package br.rv.buscacao.view.logado.cao.editar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mvc.imagepicker.ImagePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.rv.buscacao.R;
import br.rv.buscacao.config.Config;
import br.rv.buscacao.modelos.cao.Cao;
import br.rv.buscacao.utils.date.DatePickerFragmentNasc;
import br.rv.buscacao.utils.date.DatePickerFragmentP;
import br.rv.buscacao.utils.imagen.Imagens;
import br.rv.buscacao.utils.volley.FactorVolley;
import br.rv.buscacao.utils.volley.GsonPostRequest;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rafael on 04/11/17.
 */

public class Details_Editable extends Fragment {

    @BindView(R.id.fom_cadastro_cao_raca)
    EditText cadastrar_raca;
    @BindView(R.id.fom_cadastro_cao_nome)
    EditText cadastrar_nome;
    @BindView(R.id.fom_cadastro_cao_apelido)
    EditText cadastrar_apelido;
    @BindView(R.id.fom_cadastro_cao_data_nasc)
    EditText cadastrar_data_nasc;
    @BindView(R.id.fom_cadastro_cao_data_p)
    EditText cadastrar_data_p;
    @BindView(R.id.fom_cadastro_cao_sexo_feminino)
    RadioButton cadastrar_sexo_f;
    @BindView(R.id.fom_cadastro_cao_sexo_masculino)
    RadioButton cadastrar_sexo_m;
    @BindView(R.id.fom_cadastro_cao_resumo)
    EditText cadastrar_resumo;
    @BindView(R.id.fom_cadastro_cao_lat)
    EditText cadastrar_lat;
    @BindView(R.id.fom_cadastro_cao_lng)
    EditText cadastrar_lng;
    @BindView(R.id.image_view_cao)
    ImageView imagen_cao;

    Bitmap ImagenStore;
    String sexo = "";
    Cao cao;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // width and height will be at least 600px long (optional).
        ImagePicker.setMinQuality(600, 600);

    }

    @OnClick(R.id.fom_cadastro_cao_data_nasc)
    public void DataNasc(View v) {
        DatePickerFragmentNasc newFragment = new DatePickerFragmentNasc();
        newFragment.show(getActivity().getFragmentManager(), "DataPicker2");
    }

    @OnClick(R.id.fom_cadastro_cao_data_p)
    public void DataP(View v) {
        DatePickerFragmentP newFragment = new DatePickerFragmentP();
        newFragment.show(getActivity().getFragmentManager(), "DataPicker");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();

        View v = inflater.inflate(R.layout.fragmento_cadastrar_cao, container, false);
        if (v != null) {
            ButterKnife.bind(this, v);
            cao = (Cao) getArguments().get("cao");
            cadastrar_raca.setText(cao.getRaca());
            cadastrar_nome.setText(cao.getNome());
            cadastrar_apelido.setText(cao.getApelido());
            cadastrar_resumo.setText(cao.getResumo());
            cadastrar_data_nasc.setText(cao.getData_nasc());
            cadastrar_data_p.setText(cao.getData_p());
            cadastrar_lat.setText(cao.getLocal().getLat());
            cadastrar_lng.setText(cao.getLocal().getLng());

            if (cao.getSexo().equals("F")) {
                cadastrar_sexo_f.setChecked(true);
            } else {
                cadastrar_sexo_m.setChecked(true);
            }
            imagen_cao.setImageBitmap(Imagens.decodeBase64(cao.getImagen()));
            cadastrar_data_nasc.setFocusable(false);
            cadastrar_data_p.setFocusable(false);


        }
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap bitmap = ImagePicker.getImageFromResult(getActivity(), requestCode, resultCode, data);
        ImagenStore = bitmap;
        imagen_cao.setImageBitmap(bitmap);
    }

    @OnClick(R.id.image_view_cao)
    public void imagen(View view) {
        ImagePicker.pickImage(Details_Editable.this, "Escolha um foto do Cão");
    }

    @OnClick(R.id.form_cadastrar_cao_fb)
    public void cadastrar() {
        Toast.makeText(getContext(), Config.BD_TOKEN, Toast.LENGTH_LONG).show();
        final String URL = Config.cadastrar_cao;
        JSONObject local = new JSONObject();
        JSONObject map = new JSONObject();
        try {
            map.put("id", cao.getId());
            map.put("dono", cao.getDono());
            map.put("raca", cadastrar_raca.getText().toString());
            map.put("nome", cadastrar_nome.getText().toString());
            map.put("resumo", cadastrar_resumo.getText().toString());
            map.put("apelido", cadastrar_apelido.getText().toString());
            map.put("sexo", sexo);
            local.put("lat", cadastrar_lat.getText().toString());
            local.put("lng", cadastrar_lng.getText().toString());
            map.put("local", local);
            map.put("data_p", cadastrar_data_p.getText().toString());
            map.put("data_nasc", cadastrar_data_nasc.getText().toString());
            map.put("imagen", Imagens.encodeTobase64(ImagenStore));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String requestBody = map.toString();
        GsonPostRequest<String> req = new GsonPostRequest<String>(Request.Method.POST, URL, String.class, requestBody,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Criado", response);
                        Toast.makeText(getContext(), "Usuario Criado com Sucesso", Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Erro ao criar", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                params.put("X-API-TOKEN", sharedPreferences.getString(Config.TOKEN, ""));
                return params;
            }
        };
        FactorVolley.getInstance(getContext()).addToRequestQueue(req);

    }

    @OnClick({R.id.fom_cadastro_cao_sexo_feminino, R.id.fom_cadastro_cao_sexo_masculino})
    public void onRadioButtonClicked(RadioButton radioButton) {
        // Is the button now checked?
        boolean checked = radioButton.isChecked();

        //  Check which radio button was clicked
        switch (radioButton.getId()) {
            case R.id.fom_cadastro_cao_sexo_feminino:
                if (checked) {
                    sexo = "F";
                }
                break;
            case R.id.fom_cadastro_cao_sexo_masculino:
                if (checked) {
                    sexo = "M";
                }
                break;
        }
    }

}
