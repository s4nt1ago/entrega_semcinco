package com.s4nt1.petagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabLayout;
import com.s4nt1.petagram.adapter.MascotaAdaptador;
import com.s4nt1.petagram.adapter.PageAdapter;
import com.s4nt1.petagram.db.ConstantesBaseDatos;
import com.s4nt1.petagram.fragment.HomeFragment;
import com.s4nt1.petagram.fragment.PerfilFragment;
import com.s4nt1.petagram.pojo.Mascota;
import com.s4nt1.petagram.db.BaseDatos;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private ImageButton boton;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Mascota> registros = new ArrayList<>();
        BaseDatos db = new BaseDatos(this);
        registros = db.obtenerMascotas();

        if (registros.size()==0){

            ContentValues a = new ContentValues();
            a.put(ConstantesBaseDatos.T_MASCTOAS_NOMBRE,"Nombre Mascota 1");
            a.put(ConstantesBaseDatos.T_MASCOTAS_FOTO,R.drawable.perro_1);
            db.insertMascota(a);

             a = new ContentValues();
            a.put(ConstantesBaseDatos.T_MASCTOAS_NOMBRE,"Nombre Mascota 2");
            a.put(ConstantesBaseDatos.T_MASCOTAS_FOTO,R.drawable.perro_2);
            db.insertMascota(a);

             a = new ContentValues();
            a.put(ConstantesBaseDatos.T_MASCTOAS_NOMBRE,"Nombre Mascota 3");
            a.put(ConstantesBaseDatos.T_MASCOTAS_FOTO,R.drawable.perro_3);
            db.insertMascota(a);


        }

        Toolbar actBar = (Toolbar) findViewById(R.id.actBar);
        setSupportActionBar(actBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.huella);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewpage);
        setUpViewPager();


        if (toolbar!=null){

            setSupportActionBar(toolbar);
        }





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.menu_about:
                Intent intent_about = new Intent(this, ActivityAbout.class);
                startActivity(intent_about);
                break;

            case R.id.menu_contacto:
                Intent intent = new Intent(this, ActivityContacto.class);
                startActivity(intent);
                break;

            case R.id.mascotas_liked:
                Intent intent_liked = new Intent(this, MascotasLiked.class);
                startActivity(intent_liked);
                break;


        }
        return super.onOptionsItemSelected(item);
    }


    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new HomeFragment());
        fragments.add(new PerfilFragment());
        return fragments;
    }

    public void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.perfil);
    }
}