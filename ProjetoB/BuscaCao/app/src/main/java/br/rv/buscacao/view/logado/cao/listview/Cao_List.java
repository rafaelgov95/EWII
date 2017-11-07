package br.rv.buscacao.view.logado.cao.listview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.rv.buscacao.R;
import br.rv.buscacao.adapter.CaoAdapter;
import br.rv.buscacao.config.Config;
import br.rv.buscacao.modelos.cao.Cao;
import br.rv.buscacao.utils.volley.FactorVolley;
import br.rv.buscacao.utils.volley.GsonPostRequest;
import br.rv.buscacao.utils.volley.GsonRequest;
import br.rv.buscacao.view.logado.cao.cadastrar.AdicionarCao;
import br.rv.buscacao.view.logado.cao.details.Details;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;

/**
 * Created by rafael on 04/11/17.
 */

public class Cao_List extends Fragment {

    @BindView(R.id.caes_list)
    ListView listView;
    List<Cao> caes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmento_cao_list, container, false);
        if (v != null) {
            ButterKnife.bind(this, v);
        }
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getCaes();
    }



    private void getCaes() {
        String URL = Config.my_get_all;
        final Gson gson = new Gson();
        HashMap<String, String> params = new HashMap<String, String>();
        List<Cao> aux = new ArrayList<Cao>();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        params.put("X-API-TOKEN", sharedPreferences.getString(Config.TOKEN, ""));
        GsonRequest<String> req = new GsonRequest<String>( URL, String.class,params,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        caes = gson.fromJson(response, new TypeToken<List<Cao>>(){}.getType());
                        CaoAdapter adapter = new CaoAdapter(caes,getContext());
                        listView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Erro ao criar", Toast.LENGTH_LONG).show();

                    }
                });
        FactorVolley.getInstance(getContext()).addToRequestQueue(req);
    }


    @OnItemClick(R.id.caes_list)
    public void onItem(int i) {
//        Toast.makeText(getContext(), i, Toast.LENGTH_LONG).show();

        Fragment myFrag = new Details();
        Bundle bundle = new Bundle();
        bundle.putSerializable("cao", caes.get(i));
        myFrag.setArguments(bundle);
        FragmentTransaction tf = getActivity().getSupportFragmentManager().beginTransaction();
        tf.replace(R.id.container_logado, myFrag, "fadsfa");
        tf.commitAllowingStateLoss();
        Log.i("Testando",String.valueOf(i));

       }

    @OnItemLongClick(R.id.caes_list)
    public boolean onLong(int i) {
//        Toast.makeText(getActivity(), i, Toast.LENGTH_LONG).show();
        Log.i("Testando",String.valueOf(i));
        return true;
    }


}
