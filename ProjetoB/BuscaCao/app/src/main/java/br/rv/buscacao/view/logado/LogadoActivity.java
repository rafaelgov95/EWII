package br.rv.buscacao.view.logado;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.auth0.android.jwt.JWT;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.materialize.util.UIUtils;

import br.rv.buscacao.R;
import br.rv.buscacao.config.Config;
import br.rv.buscacao.view.logado.cao.listview.Cao_List;
import br.rv.buscacao.view.logado.cao.listview.Cao_List_Global;
import br.rv.buscacao.view.logado.maps.Maps;
import br.rv.buscacao.view.logado.maps.My_Maps;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

public class LogadoActivity extends AppCompatActivity {
    private static final int PROFILE_SETTING = 1;

    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logado);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        Config.BD_TOKEN = sharedPreferences.getString(Config.TOKEN, "");
        String user_name= sharedPreferences.getString(Config.USUARIO, "");
        String user_email= sharedPreferences.getString(Config.EMAIL, "");
        // Handle Toolbar

         DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.dismissPopupMenus();
        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("teste");

        // Create a few sample profile
        final IProfile profile = new ProfileDrawerItem().withName(user_name).withEmail(user_email).withIcon(R.drawable.profile5);


        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        profile

                        //don't ask but google uses 14dp for the add account icon in gmail but 20dp for the normal icons (like manage account)
//                        new ProfileSettingDrawerItem().withName("Adicionar Conta").withDescription("Add new GitHub Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_add).actionBar().paddingDp(5).colorRes(R.color.material_drawer_dark_primary_text)).withIdentifier(PROFILE_SETTING),
//                        new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings)
                )
                .withSavedInstance(savedInstanceState)
                .build();

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_mapa_global).withIcon(GoogleMaterial.Icon.gmd_map).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_my_mapa).withIcon(FontAwesome.Icon.faw_map).withIdentifier(2),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_global_list).withIcon(FontAwesome.Icon.faw_list).withIdentifier(3),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_my_list).withIcon(FontAwesome.Icon.faw_list).withIdentifier(4),

                        new SectionDrawerItem().withName(R.string.drawer_item_section_header),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_cog),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_help).withIcon(FontAwesome.Icon.faw_question).withEnabled(false),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_contact).withIcon(FontAwesome.Icon.faw_bullhorn),
            new SecondaryDrawerItem().withName(R.string.drawer_item_sair).withIcon(GoogleMaterial.Icon.gmd_exit_to_app).withIdentifier(5)

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null && drawerItem.getIdentifier() == 1) {
                            Log.i("TOKEN", Config.BD_TOKEN);
                            try {
//                                String jwtString = Jwts.builder().signWith(SignatureAlgorithm.HS512, Config.Secret).compact();
                                 Log.i("TOKEN",Jwts.parser()
                                         .requireSubject("ME")
                                         .require("custom", "myCustom")
                                         .setSigningKey(Config.TOKEN)
                                         .parseClaimsJws(Config.BD_TOKEN).getBody().toString());

//                                JWT jwt = new JWT(Config.BD_TOKEN);
//                                Log.i("TOKEN", jwt.getClaim("token").asString()   );

                            }catch (Exception e){

                            }
                            showFragment(new Maps(), "Mapa Global");
                        }
                        if (drawerItem != null && drawerItem.getIdentifier() == 2) {
                            showFragment(new My_Maps(), "Meu Mapa");
                        }
                        if (drawerItem != null && drawerItem.getIdentifier() == 3) {
                            showFragment(new Cao_List_Global(), "Lista Global de Cães");
                        }
                        if (drawerItem != null && drawerItem.getIdentifier() == 4) {
                            showFragment(new Cao_List(), "Meus Cães");
                        }
                        if (drawerItem != null && drawerItem.getIdentifier() == 5) {
                            SharedPreferences sharedPreferences = LogadoActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);
                            editor.putString(Config.TOKEN, "");
                            editor.commit();
                            finish();
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        // set the selection to the item with the identifier 5
        if (savedInstanceState == null) {
            result.setSelection(5, false);
        }

        //set the back arrow in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction tf = fragmentManager.beginTransaction();
        tf.add(R.id.container_logado, new Maps(), "MapsFragment");
        tf.commitAllowingStateLoss();


    }

    private void showFragment(Fragment fragment, String name) {
        FragmentTransaction tf = fragmentManager.beginTransaction();
        tf.replace(R.id.container_logado, fragment, name);
        tf.commitAllowingStateLoss();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle the click on the back arrow click
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            super.onBackPressed();
        } else {
            showFragment(new Maps(), "Maps");
        }
    }

}
