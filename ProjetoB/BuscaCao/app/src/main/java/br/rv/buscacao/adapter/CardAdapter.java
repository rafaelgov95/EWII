package br.rv.buscacao.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.rv.buscacao.R;
import br.rv.buscacao.adapter.viewholder.CardHolder;
import br.rv.buscacao.modelos.cao.Cao;

/**
 * Created by rafael on 15/11/16.
 */

public class CardAdapter extends RecyclerView.Adapter<CardHolder> {

    private final List<Cao> mUsers;

    public CardAdapter(ArrayList users) {
        mUsers = users;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_card_view, parent, false));
    }

    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
        holder.title.setText(String.format(Locale.getDefault(), "%s, %d - %s",
                mUsers.get(position).getNome(),
                mUsers.get(position).getDono(),
                mUsers.get(position).getNome()
        ));
//        holder.desc.setText(mUsers.get(position).getDescription());
//        holder.moreButton.setOnClickListener(view -> updateItem(position));
//        holder.deleteButton.setOnClickListener(view -> removerItem(position));
    }

    @Override
    public int getItemCount() {
        return mUsers != null ? mUsers.size() : 0;
    }

    private void insertItem(Cao user) {
        mUsers.add(user);
        notifyItemInserted(getItemCount());
    }

    private void updateItem(int position) {
//        mUsers.get(position).incrementAge();
        notifyItemChanged(position);
    }

    private void removerItem(int position) {
        mUsers.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mUsers.size());
    }

    public void updateList(Cao user) {
        insertItem(user);
    }
}


