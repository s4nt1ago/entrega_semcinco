package com.s4nt1.petagram.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.s4nt1.petagram.pojo.Mascota;
import com.s4nt1.petagram.R;

import java.util.ArrayList;


public class FotosAdaptador extends RecyclerView.Adapter<FotosAdaptador.FotosViewHolder> {
    ArrayList<Mascota> mascotas;

    public FotosAdaptador(ArrayList<Mascota> mascotas){
        this.mascotas = mascotas;
    }
    @NonNull
    @Override
    public FotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_grid, parent, false);
        return new FotosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FotosViewHolder holder, int position) {
        Mascota mascota = mascotas.get(position);
        holder.imagen.setImageResource(mascota.getImagen());
        holder.cantidad_likes.setText(mascota.getCantidad_likes()+"");
        holder.nombre.setText(mascota.getNombre());
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class FotosViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagen;
        private TextView nombre;
        private TextView cantidad_likes;

        public FotosViewHolder(View itemView){
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
            nombre = (TextView) itemView.findViewById(R.id.name);
            cantidad_likes = (TextView) itemView.findViewById(R.id.cantidad_likes);
        }


    }

}