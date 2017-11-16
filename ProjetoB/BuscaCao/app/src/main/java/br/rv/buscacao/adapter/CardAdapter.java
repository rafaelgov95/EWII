package br.rv.buscacao.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.rv.buscacao.R;
import br.rv.buscacao.adapter.viewholder.CardHolder;
import br.rv.buscacao.config.Config;
import br.rv.buscacao.modelos.cao.Cao;
import br.rv.buscacao.utils.volley.FactorVolley;
import br.rv.buscacao.utils.volley.GsonPostRequest;

/**
 * Created by rafael on 15/11/16.
 */

public class CardAdapter extends RecyclerView.Adapter<CardHolder> {

    private final Cao cao;

    public CardAdapter(Cao cao) {
        this.cao = cao;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragmento_cao_detalhe, parent, false));
    }

    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
        holder.id.setText(cao.getId().toString());
        holder.cao.setText(cao.getNome());
        holder.raca.setText(cao.getRaca());
        holder.dono.setText(cao.getDono());
        holder.nome.setText(cao.getNome());
        holder.apelido.setText(cao.getApelido());
        holder.resumo.setText(cao.getResumo());
        holder.data_create.setText(cao.getData_create());
        holder.data_nasc.setText(cao.getData_nasc());
        holder.data_p.setText(cao.getData_p());
        holder.sexo.setText(cao.getSexo());

    }

    @Override
    public int getItemCount() {
        return cao != null ? 1 : 0;
    }


    private void updateItem(int position) {
        notifyItemChanged(position);
    }


}


