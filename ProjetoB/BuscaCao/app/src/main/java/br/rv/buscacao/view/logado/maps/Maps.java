package br.rv.buscacao.view.logado.maps;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import br.rv.buscacao.R;
import br.rv.buscacao.view.logado.cao.cadastrar.AdicionarCao;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Maps extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    @BindView(R.id.mapView)
    MapView mMapView;
    private GoogleMap googleMap;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmento_maps, container, false);
        if (rootView != null) {
            ButterKnife.bind(this, rootView);
        }
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                try {
                    googleMap.setOnMapClickListener(Maps.this);
//                    Criteria criteria = new Criteria();
//                    LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
//                    String provider = locationManager.getBestProvider(criteria, false);
//                    Location location = locationManager.getLastKnownLocation(provider);
//                    double lat = location.getLatitude();
//                    double lng = location.getLongitude();
//                    LatLng coordinate = new LatLng(lat, lng);
                    googleMap.setMyLocationEnabled(true);
//                    googleMap.addMarker(new MarkerOptions().position(coordinate).title("Marker Title").snippet("Marker Description"));
//                    CameraPosition cameraPosition = new CameraPosition.Builder().target(coordinate).zoom(12).build();
//                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                } catch (SecurityException ex) {
                    Log.i("Erro", "Map erro location", ex);
                }
            }
        });

        return rootView;
    }


    @OnClick(R.id.maps_fb)
    public void cadastrar() {
        Toast.makeText(getContext(), "Vamos que vamos", Toast.LENGTH_SHORT).show();

    }

    private void showFragment(Fragment fragment, String name) {
        FragmentTransaction tf = getActivity().getSupportFragmentManager().beginTransaction();
        tf.replace(R.id.container_logado, fragment, name);
        tf.commitAllowingStateLoss();

    }

    @Override
    public void onMapClick(LatLng latLng) {
        Fragment myFrag = new AdicionarCao();
        Bundle bundle = new Bundle();
        bundle.putDouble("lat", latLng.latitude);
        bundle.putDouble("lng", latLng.longitude);
        myFrag.setArguments(bundle);
        FragmentTransaction tf = getActivity().getSupportFragmentManager().beginTransaction();
        tf.replace(R.id.container_logado, myFrag, "fadsfa");
        tf.commitAllowingStateLoss();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }


}