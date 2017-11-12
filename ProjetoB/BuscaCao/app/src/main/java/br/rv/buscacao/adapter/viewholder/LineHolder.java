package br.rv.buscacao.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.facebook.drawee.view.SimpleDraweeView;

import br.rv.buscacao.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rafael on 15/11/16.
 */

public class LineHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.thumbnail)
    public SimpleDraweeView imagen;
    @BindView(R.id.cao_data_nome)
    public TextView nome;
    @BindView(R.id.cao_data_apelido)
    public TextView apelido;
    @BindView(R.id.cao_data_date_nasc)
    public TextView date_nasc;
    @BindView(R.id.cao_data_date_p)
    public TextView date_p;

    public LineHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
