package br.rv.buscacao.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import br.rv.buscacao.R;
import br.rv.buscacao.adapter.viewholder.LineHolder;
import br.rv.buscacao.config.Config;
import br.rv.buscacao.modelos.cao.Cao;
import br.rv.buscacao.utils.imagen.Imagens;
import br.rv.buscacao.utils.volley.FactorVolley;
import br.rv.buscacao.utils.volley.GsonDeleteRequest;
import br.rv.buscacao.utils.volley.GsonPostRequest;
import br.rv.buscacao.view.logado.cao.details.Details;
import br.rv.buscacao.view.logado.cao.editar.Details_Editable;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by rafael on 15/11/16.
 */

public class LineAdapter extends RecyclerView.Adapter<LineHolder> implements View.OnClickListener {

    private final List<Cao> caes;
    private Context contexto;
    private FragmentTransaction tf;

    public LineAdapter(ArrayList users,FragmentTransaction tf) {
        caes = users;
        this.tf = tf;
    }

    @Override
    public LineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        contexto = parent.getContext();
        return new LineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragmento_cao_data, parent, false));
    }

    @Override
    public void onBindViewHolder(final LineHolder holder, final int position) {
        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment myFrag = new Details();
                Bundle bundle = new Bundle();
                bundle.putSerializable("cao", caes.get(position));
                myFrag.setArguments(bundle);
                tf.replace(R.id.container_logado, myFrag, "fadsfa");
                tf.commitAllowingStateLoss();
            }
        });

        holder.btn_opt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating a popup menu
                PopupMenu popup = new PopupMenu(contexto, holder.btn_opt);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_data_cao);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Log.i("botao", "possicao: " + position + "  " + "item " + item.getItemId());
                        switch (item.getItemId()) {
                            case R.id.action_cao_edite:
                                updateItem(position);
                                break;
                            case R.id.action_cao_delete:
                                removerItem(position);
                                break;

                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });
        holder.imagen.setImageURI(caes.get(position).getImagen());
        holder.nome.setText(caes.get(position).getNome());
        holder.dono.setText(caes.get(position).getDono());
        holder.date_p.setText(caes.get(position).getData_p());
        holder.date_nasc.setText(caes.get(position).getData_nasc());
    }

    @Override
    public int getItemCount() {
        return caes != null ? caes.size() : 0;
    }


    private void updateItem(int position) {
        Fragment myFrag = new Details_Editable();
        Bundle bundle = new Bundle();
        bundle.putSerializable("cao", caes.get(position));
        myFrag.setArguments(bundle);
        tf.replace(R.id.container_logado, myFrag, "fadsfa");
        tf.commitAllowingStateLoss();
    }

    private void removerItem(final int position) {
        final String URL = Config.dono_cao_delete;
        Map<String,String> heads = new HashMap<>();
        String id = caes.get(position).getId();
        SharedPreferences sharedPreferences = contexto.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        heads.put("X-API-TOKEN", sharedPreferences.getString(Config.TOKEN, ""));
        GsonDeleteRequest<String> req = new GsonDeleteRequest<String>(URL, String.class,heads,id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(contexto, "Removido Com Sucesso", Toast.LENGTH_LONG).show();
                        caes.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, caes.size());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(contexto, "Erro ao criar", Toast.LENGTH_LONG).show();

                    }
                });
        FactorVolley.getInstance(contexto).addToRequestQueue(req);

    }

    @Override
    public void onClick(View v) {

    }
}


