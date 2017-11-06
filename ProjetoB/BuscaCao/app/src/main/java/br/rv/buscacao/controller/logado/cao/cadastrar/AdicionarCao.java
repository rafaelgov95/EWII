package br.rv.buscacao.controller.logado.cao.cadastrar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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
import br.rv.buscacao.utils.imagen.Imagens;
import br.rv.buscacao.utils.volley.FactorVolley;
import br.rv.buscacao.utils.volley.GsonPostRequest;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rafael on 04/11/17.
 */

public class AdicionarCao extends Fragment {
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
    CheckBox cadastrar_sexo_f;
    @BindView(R.id.fom_cadastro_cao_sexo_masculino)
    CheckBox cadastrar_sexo_m;
    @BindView(R.id.fom_cadastro_cao_resumo)
    EditText cadastrar_resumo;
    @BindView(R.id.image_view_cao)
    ImageView imagen_cao;

    Bitmap ImagenStore ;
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // width and height will be at least 600px long (optional).
        ImagePicker.setMinQuality(600, 600);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Bundle args = getArguments();
        System.out.println(args.get("lat"));
        System.out.println(args.get("lng"));
        View v = inflater.inflate(R.layout.fragmento_cadastri_cao, container, false);
        if (v != null)
        {
            ButterKnife.bind(this, v);

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
    public void imagen (View view) {
        ImagePicker.pickImage(AdicionarCao.this, "Escolha um foto do Cão");
    }

    @OnClick(R.id.form_cadastrar_cao_fb)
    public void cadastrar() {
        Toast.makeText(getContext(),Config.BD_TOKEN,Toast.LENGTH_LONG).show();
        final String URL = Config.cadastrar_cao;
        JSONObject map = new JSONObject();
        try {
            map.put("raca", cadastrar_raca.getText().toString());
            map.put("nome", cadastrar_nome.getText().toString());
            map.put("resumo", cadastrar_resumo.getText().toString());
            map.put("apelido", cadastrar_apelido.getText().toString());
            map.put("sexo", cadastrar_sexo_m.getText().toString());
            if(ImagenStore!=null)
                map.put("imagen", Imagens.encodeTobase64(ImagenStore));
//                map.put("local", cadastrar_pwd1.getText().toString());
//            map.put("data_nasc", cadastrar_data_nasc.getText().toString());
//            map.put("data_nasc", cadastrar_data_nasc.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String requestBody = map.toString();
        GsonPostRequest<String> req = new GsonPostRequest<String>(Request.Method.POST, URL,String.class, requestBody,
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
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                params.put("X-API-TOKEN",sharedPreferences.getString(Config.TOKEN, ""));
                return params;
            }
        };
        FactorVolley.getInstance(getContext()).addToRequestQueue(req);

    }

}
