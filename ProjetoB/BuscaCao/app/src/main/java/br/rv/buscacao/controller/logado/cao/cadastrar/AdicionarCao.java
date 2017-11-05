package br.rv.buscacao.controller.logado.cao.cadastrar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import br.rv.buscacao.R;
import butterknife.ButterKnife;

/**
 * Created by rafael on 04/11/17.
 */

public class AdicionarCao extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragmento_cadastri_cao, container, false);
        if (v != null)
        {
            ButterKnife.bind(this, v);
        }
        return v;
    }
}
