package br.rv.buscacao.view.logado.cao.listview;

import android.content.Context;

import android.content.SharedPreferences;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import br.rv.buscacao.R;
import br.rv.buscacao.adapter.LineAdapter;
import br.rv.buscacao.config.Config;
import br.rv.buscacao.modelos.cao.Cao;
import br.rv.buscacao.utils.volley.FactorVolley;
import br.rv.buscacao.utils.volley.GsonRequest;
import br.rv.buscacao.view.logado.cao.details.Details;
import br.rv.buscacao.view.logado.cao.editar.Details_Editable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rafael on 04/11/17.
 */

public class Cao_List extends Fragment {


    @BindView(R.id.caes_list)
     RecyclerView listView ;
     ArrayList<Cao> caes = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Fresco.initialize(getContext());

        View v =  inflater.inflate(R.layout.fragmento_cao_list, container, false);

        if (v != null) {
            ButterKnife.bind(this, v);
            listView.setHasFixedSize(true);
            listView.setLayoutManager(new LinearLayoutManager(getActivity()));
            listView.setItemAnimator(new DefaultItemAnimator());
            listView.setAdapter(new LineAdapter(caes));
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
                        Log.i("GetCaes","Chego");
                        caes = gson.fromJson(response, new TypeToken<List<Cao>>(){}.getType());
                        Log.i("LOL", String.valueOf(caes.size()));

                        listView.setAdapter(new LineAdapter(caes));
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


//    @OnItemClick(R.id.caes_list)
    public void onItem(int i) {
        Fragment myFrag = new Details();
        Bundle bundle = new Bundle();
        bundle.putSerializable("cao", caes.get(i));
        myFrag.setArguments(bundle);
        FragmentTransaction tf = getActivity().getSupportFragmentManager().beginTransaction();
        tf.replace(R.id.container_logado, myFrag, "fadsfa");
        tf.commitAllowingStateLoss();
        Log.i("Testando",String.valueOf(i));

       }

//    @OnItemLongClick(R.id.caes_list)
    public boolean onLong(int i) {
        Fragment myFrag = new Details_Editable();
        Bundle bundle = new Bundle();
        bundle.putSerializable("cao", caes.get(i));
        myFrag.setArguments(bundle);
        FragmentTransaction tf = getActivity().getSupportFragmentManager().beginTransaction();
        tf.replace(R.id.container_logado, myFrag, "fadsfa");
        tf.commitAllowingStateLoss();
        Log.i("Testando",String.valueOf(i));
        return true;
    }


}
