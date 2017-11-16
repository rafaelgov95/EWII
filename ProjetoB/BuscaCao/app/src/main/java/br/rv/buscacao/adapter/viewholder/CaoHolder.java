package br.rv.buscacao.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import br.rv.buscacao.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rafael on 15/11/16.
 */

public class CaoHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.cao_list_click)
    public RelativeLayout layout;
    @BindView(R.id.thumbnail)
    public SimpleDraweeView imagen;
    @BindView(R.id.cao_data_nome)
    public TextView nome;
    @BindView(R.id.cao_data_dono)
    public TextView dono;
    @BindView(R.id.cao_data_date_nasc)
    public TextView date_nasc;
    @BindView(R.id.cao_data_date_p)
    public TextView date_p;
    @BindView(R.id.ib_popup_menu)
    public ImageButton btn_opt;

    public CaoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

}
