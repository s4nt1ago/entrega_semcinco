package com.s4nt1.petagram.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.s4nt1.petagram.db.BaseDatos;
import com.s4nt1.petagram.pojo.Mascota;
import com.s4nt1.petagram.R;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {
    ArrayList<Mascota> mascotas;

    public MascotaAdaptador(ArrayList<Mascota> mascotas){
        this.mascotas = mascotas;
    }
    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);
        holder.imagen.setImageResource(mascota.getImagen());
        holder.cantidad_likes.setText(mascota.getCantidad_likes()+"");
        holder.nombre.setText(mascota.getNombre());

        holder.btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDatos db = new BaseDatos(v.getContext());
                db.darLike(mascota.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagen;
        private TextView nombre;
        private TextView cantidad_likes;
        private ImageButton btn_like;

        public MascotaViewHolder(View itemView){
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
            nombre = (TextView) itemView.findViewById(R.id.name);
            cantidad_likes = (TextView) itemView.findViewById(R.id.cantidad_likes);
            btn_like = (ImageButton) itemView.findViewById(R.id.btn_like);
        }


    }

}