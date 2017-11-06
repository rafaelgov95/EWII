package br.rv.buscacao.controller.logado;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.rv.buscacao.R;
import br.rv.buscacao.config.Config;
import br.rv.buscacao.controller.logado.cao.cadastrar.AdicionarCao;
import br.rv.buscacao.controller.logado.cao.listview.Cao_List;
import br.rv.buscacao.controller.logado.maps.Maps;


public class LogadoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        Config.BD_TOKEN = sharedPreferences.getString(Config.TOKEN, "");
        Toast.makeText(this,Config.BD_TOKEN,Toast.LENGTH_LONG).show();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction tf = fragmentManager.beginTransaction();
        tf.add(R.id.container_logado, new Maps(), "MapsFragment");
        tf.commitAllowingStateLoss();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


        } else if (id == R.id.action_logout) {
            SharedPreferences sharedPreferences = LogadoActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            // Creating editor to store values to SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            // Adding values to editor
            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);
            editor.putString(Config.TOKEN, "");
            // Saving values to editor
            editor.commit();
            // Starting profile activity
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showFragment(Fragment fragment,String name) {
        FragmentTransaction tf = fragmentManager.beginTransaction();
        tf.replace(R.id.container_logado,fragment,name);
        tf.commitAllowingStateLoss();

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            showFragment(new AdicionarCao(),"Fragmento Mapa ");
        }
        if (id == R.id.nav_gallery) {
            showFragment(new Cao_List(),"Fragmento Mapa ");
        }
        if (id == R.id.nav_view) {
            showFragment(new Maps(),"Fragmento Mapa ");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
