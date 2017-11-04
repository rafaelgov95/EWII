package br.rv.buscacao.controller.logado.maps;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.rv.buscacao.R;
import br.rv.buscacao.controller.logado.cao.cadastrar.AdicionarCao;

public class MapsFragmento extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private FragmentManager fragmentManager;
    private Context Contexto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
        Contexto = getContext();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {

            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, true);
            Toast.makeText(getActivity(), "Provider: " + provider, Toast.LENGTH_SHORT).show();
            mMap = googleMap;
            mMap.setOnMapClickListener(this);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.setMyLocationEnabled(true);
        } catch (SecurityException ex) {
            Log.i("Erro", "Map erro location", ex);
        }
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    @Override
    public void onMapClick(LatLng latLng) {
        Intent intent = new Intent(Contexto, AdicionarCao.class);
        Bundle bundle = new Bundle();

        bundle.putString("latlng", latLng.toString());
        intent.putExtras(bundle);
        startActivity(intent);
        Toast.makeText(getContext(), "Coordenadas: " + latLng.toString(), Toast.LENGTH_SHORT).show();

    }
}
