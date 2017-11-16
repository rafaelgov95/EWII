package br.rv.buscacao.view.logado.cao.cadastrar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import br.rv.buscacao.utils.date.DatePickerFragmentNasc;
import br.rv.buscacao.utils.date.DatePickerFragmentP;
import br.rv.buscacao.utils.imagen.Imagens;
import br.rv.buscacao.utils.volley.FactorVolley;
import br.rv.buscacao.utils.volley.GsonPostRequest;
import br.rv.buscacao.view.logado.maps.My_Maps;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rafael on 04/11/17.
 */

public class AdicionarCao extends Fragment {
    @BindView(R.id.fom_cadastro_user_name)
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
    @BindView(R.id.progressBar_ADD)
    ProgressBar progressBar;

    Bitmap ImagenStore;
    String sexo= "";
//
//    public AdicionarCao() {
//        a = new UniversalLoaderBase64();
//    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImagePicker.setMinQuality(100, 100);


    }

    @OnClick(R.id.fom_cadastro_cao_data_nasc)
    public void DataNasc(View v) {
        DatePickerFragmentNasc newFragment = new DatePickerFragmentNasc();
        newFragment.show(getActivity().getFragmentManager(),"DataPicker2");
    }

    @OnClick(R.id.fom_cadastro_cao_data_p)
    public void DataP(View v) {
        DatePickerFragmentP newFragment = new DatePickerFragmentP();
        newFragment.show(getActivity().getFragmentManager(),"DataPicker");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();

        View v = inflater.inflate(R.layout.fragmento_cadastrar_cao, container, false);
        if (v != null) {
            ButterKnife.bind(this, v);
            progressBar.setVisibility(View.GONE);
            cadastrar_data_p.setFocusable(false);
            cadastrar_data_nasc.setFocusable(false);
            cadastrar_lat.setText(String.valueOf(args.get("lat")));
            cadastrar_lng.setText(String.valueOf(args.get("lng")));
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
        ImagePicker.pickImage(AdicionarCao.this, "Escolha um foto do Cão");
    }

    @OnClick(R.id.form_cadastrar_cao_fb)
    public void cadastrar() {
        progressBar.setVisibility(View.VISIBLE);

        final String URL = Config.cadastrar_cao;

        JSONObject local = new JSONObject();
        JSONObject map = new JSONObject();
        try {
            map.put("raca", cadastrar_raca.getText().toString());
            map.put("nome", cadastrar_nome.getText().toString());
            map.put("resumo", cadastrar_resumo.getText().toString());
            map.put("apelido", cadastrar_apelido.getText().toString());
            map.put("sexo",sexo);
            local.put("lat", cadastrar_lat.getText().toString());
            local.put("lng", cadastrar_lng.getText().toString());
            map.put("local", local);

            map.put("data_p", cadastrar_data_p.getText().toString());
            map.put("data_nasc", cadastrar_data_nasc.getText().toString());
            if (ImagenStore != null)
                map.put("imagen", Imagens.encodeTobase64(ImagenStore));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String requestBody = map.toString();
        GsonPostRequest<String> req = new GsonPostRequest<String>(Request.Method.POST, URL, String.class, requestBody,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), "Adicionado ao Mapa!", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                        Fragment f = new My_Maps();
                        showFragment(f,"Maps");

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Erro!", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);

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
    private void showFragment(Fragment fragment, String name) {
        FragmentTransaction tf = getActivity().getSupportFragmentManager().beginTransaction();
        tf.replace(R.id.container_logado, fragment, name);
        tf.commitAllowingStateLoss();

    }
    @OnClick({ R.id.fom_cadastro_cao_sexo_feminino, R.id.fom_cadastro_cao_sexo_masculino }) public void onRadioButtonClicked(RadioButton radioButton) {
        // Is the button now checked?
        boolean checked = radioButton.isChecked();

        // Check which radio button was clicked
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
