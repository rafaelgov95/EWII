package br.rv.buscacao.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;


import com.facebook.drawee.view.SimpleDraweeView;

import br.rv.buscacao.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rafael on 15/11/16.
 */

public class CardHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.details_id)
   public TextView id;
    @BindView(R.id.details_dono)
    public TextView dono;
    @BindView(R.id.details_nome)
    public   TextView nome;
    @BindView(R.id.details_apelido)
    public   TextView apelido;
    @BindView(R.id.details_raca)
    public TextView raca;
    @BindView(R.id.details_data_nasc)
    public  TextView data_nasc;
    @BindView(R.id.details_data_p)
    public  TextView data_p;
    @BindView(R.id.details_resumo)
    public  TextView resumo;
    @BindView(R.id.details_data_create)
    public   TextView data_create;
// RadioButton cadastrar_sexo_f;
// @BindView(R.id.deta)
// RadioButton cadastrar_sexo_m;


    public CardHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
