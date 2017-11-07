package br.rv.buscacao.view.logado.cao.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvc.imagepicker.ImagePicker;

import br.rv.buscacao.R;
import butterknife.ButterKnife;

/**
 * Created by rafael on 03/11/17.
 */

public class Details extends Fragment {

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
        }
        return v;
    }

}
