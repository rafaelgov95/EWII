package br.rv.buscacao.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.rv.buscacao.R;
import br.rv.buscacao.adapter.viewholder.LineHolder;
import br.rv.buscacao.modelos.cao.Cao;
import br.rv.buscacao.utils.imagen.Imagens;

/**
 * Created by rafael on 15/11/16.
 */

public class LineAdapter extends RecyclerView.Adapter<LineHolder> {

    private final List<Cao> caes;

    public LineAdapter(ArrayList users) {
        caes = users;
    }

    @Override
    public LineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragmento_cao_data, parent, false));
    }

    @Override
    public void onBindViewHolder(LineHolder holder, int position) {

        holder.imagen.setImageURI(caes.get(position).getImagen());
        Log.i("Onbid",caes.get(position).getImagen());
        holder.nome.setText(caes.get(position).getNome());
        holder.apelido.setText(caes.get(position).getApelido());
        holder.date_p.setText(caes.get(position).getData_p() );
        holder.date_nasc.setText(caes.get(position).getData_nasc());
    }

    @Override
    public int getItemCount() {
        return caes != null ? caes.size() : 0;
    }

    /**
     * Método publico chamado para atualziar a lista.
     *
     * @param user Novo objeto que será incluido na lista.
     */
    public void updateList(Cao user) {
        insertItem(user);
    }

    // Método responsável por inserir um novo usuário na lista e notificar que há novos itens.
    private void insertItem(Cao user) {
        caes.add(user);
        notifyItemInserted(getItemCount());
    }

    // Método responsável por atualizar um usuário já existente na lista.
    private void updateItem(int position) {
        Cao userModel = caes.get(position);
        userModel.getSexo();
        notifyItemChanged(position);
    }

    // Método responsável por remover um usuário da lista.
    private void removerItem(int position) {
        caes.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, caes.size());
    }
}


