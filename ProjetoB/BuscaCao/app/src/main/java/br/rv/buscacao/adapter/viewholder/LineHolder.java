package br.rv.buscacao.adapter.viewholder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.drawee.view.SimpleDraweeView;

import br.rv.buscacao.R;
import br.rv.buscacao.view.logado.cao.details.Details;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rafael on 15/11/16.
 */

public class LineHolder extends RecyclerView.ViewHolder {
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

    public LineHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
