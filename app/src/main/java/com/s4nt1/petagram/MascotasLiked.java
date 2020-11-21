package com.s4nt1.petagram;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.s4nt1.petagram.adapter.MascotaAdaptador;
import com.s4nt1.petagram.db.BaseDatos;
import com.s4nt1.petagram.pojo.Mascota;

import java.util.ArrayList;

public class MascotasLiked extends AppCompatActivity {
    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_liked);
        Toolbar actBar = (Toolbar) findViewById(R.id.actBar);
        setSupportActionBar(actBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.huella);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaMascotas = (RecyclerView) findViewById(R.id.recyc_like);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);

        BaseDatos db = new BaseDatos(this);
        mascotas = db.obtenerMascotasLike();
        inicilizarAdaptador();


    }

    public void  inicilizarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas);
        listaMascotas.setAdapter(adaptador);
    }


}
