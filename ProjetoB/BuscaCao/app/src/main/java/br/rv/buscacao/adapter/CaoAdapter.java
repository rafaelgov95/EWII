package br.rv.buscacao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import br.rv.buscacao.R;
import br.rv.buscacao.modelos.cao.Cao;
import br.rv.buscacao.utils.imagen.Imagens;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jose on 25/06/16.
 */
public class CaoAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<Cao> caoes;

    public CaoAdapter(List<Cao> itens, Context context) {
        this.caoes = itens;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return caoes.size();
    }

    @Override
    public Object getItem(int i) {
        return caoes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Cao l = caoes.get(i);
        ViewHolder holder;

        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = layoutInflater.inflate(R.layout.fragmento_cao_data,
                    viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }



        holder.imagen.setImageBitmap(Imagens.decodeBase64(l.getImagen()));
        holder.nome.setText(l.getNome());
        holder.apelido.setText(l.getApelido());
        holder.date_p.setText(l.getData_p() );
        holder.date_nasc.setText(l.getData_nasc());

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.thumbnail)
        ImageView imagen;
        @BindView(R.id.cao_data_nome)
        TextView nome;
        @BindView(R.id.cao_data_apelido)
        TextView apelido;
        @BindView(R.id.cao_data_date_nasc)
        TextView date_nasc;
        @BindView(R.id.cao_data_date_p)
        TextView date_p;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
