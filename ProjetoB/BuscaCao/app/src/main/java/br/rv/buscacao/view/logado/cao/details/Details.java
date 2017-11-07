package br.rv.buscacao.view.logado.cao.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.rv.buscacao.R;
import br.rv.buscacao.modelos.cao.Cao;
import br.rv.buscacao.utils.imagen.Imagens;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rafael on 03/11/17.
 */

public class Details extends Fragment {

    @BindView(R.id.details_id)
    TextView id;
    @BindView(R.id.details_dono)
    TextView dono;
    @BindView(R.id.details_nome)
    TextView nome;
    @BindView(R.id.details_apelido)
    TextView apelido;
    @BindView(R.id.details_raca)
    TextView raca;
    @BindView(R.id.details_data_nasc)
    TextView data_nasc;
    @BindView(R.id.details_data_p)
    TextView data_p;
    @BindView(R.id.details_resumo)
    TextView resumo;
    @BindView(R.id.details_data_create)
    TextView data_create;
    @BindView(R.id.details_image_view)
    ImageView imagen;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // width and height will be at least 600px long (optional).
        ButterKnife.bind(getActivity());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragmento_cao_detalhe, container, false);
        if (v != null)
        {
            ButterKnife.bind(this, v);
            Cao cao =(Cao)getArguments().get("cao");
            id.setText(cao.getId());
            raca.setText(cao.getRaca());
            dono.setText(cao.getDono());
            nome.setText(cao.getNome());
            imagen.setImageBitmap(Imagens.decodeBase64(cao.getImagen()));
            apelido.setText(cao.getApelido());
            resumo.setText(cao.getResumo());
            data_create.setText(cao.getData_create());
            data_nasc.setText(cao.getData_nasc());
            data_p.setText(cao.getData_p());
        }
        return v;
    }

}
