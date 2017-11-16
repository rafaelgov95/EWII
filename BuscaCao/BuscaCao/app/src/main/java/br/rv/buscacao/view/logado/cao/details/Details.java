package br.rv.buscacao.view.logado.cao.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import br.rv.buscacao.R;
import br.rv.buscacao.adapter.CardAdapter;
import br.rv.buscacao.adapter.LineAdapter;
import br.rv.buscacao.modelos.cao.Cao;
import br.rv.buscacao.utils.imagen.Imagens;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rafael on 03/11/17.
 */

public class Details extends Fragment {

    @BindView(R.id.recycler_details)
    RecyclerView list ;
    @BindView(R.id.details_image_view_detail)
    public SimpleDraweeView imagen;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.activity_recycler_view_layout, container, false);
        if (v != null)
        {
            ButterKnife.bind(this, v);
            Cao cao = (Cao) getArguments().get("cao");
            imagen.setImageURI(cao.getImagen());
            list.setHasFixedSize(true);
            list.setLayoutManager(new LinearLayoutManager(getActivity()));
            list.setItemAnimator(new DefaultItemAnimator());
            list.setAdapter(new CardAdapter(cao));
        }
        return v;
    }

}
