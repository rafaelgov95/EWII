package br.rv.buscacao.controller.logado.cao.cadastrar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import br.rv.buscacao.R;
import butterknife.ButterKnife;

/**
 * Created by rafael on 04/11/17.
 */

public class AdicionarCao extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmento_cadastri_cao);

        ButterKnife.bind(this);

    }
}
