package com.s4nt1.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.s4nt1.petagram.pojo.Mascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context){
        super(context, ConstantesBaseDatos.DB_NAME, null, ConstantesBaseDatos.DB_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBaseDatos.T_MASCOTAS + "(" +
                ConstantesBaseDatos.T_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.T_MASCTOAS_NOMBRE + " TEXT, " +
                ConstantesBaseDatos.T_MASCOTAS_FOTO + " INTEGER" +
                ")";
        String queryCrearTablaLikesMascota = "CREATE TABLE " + ConstantesBaseDatos.T_MASCOTAS_LIKED + "(" +
                ConstantesBaseDatos.T_MASCOTAS_LIKED_ORDINAL + " INTEGER UNIQUE, " +
                ConstantesBaseDatos.T_MASCOTAS_LIEKD_ID_MASCOTA + " INTEGER UNIQUE, " +
                "FOREIGN KEY (" + ConstantesBaseDatos.T_MASCOTAS_LIEKD_ID_MASCOTA + ") " +
                "REFERENCES " + ConstantesBaseDatos.T_MASCOTAS + "(" + ConstantesBaseDatos.T_MASCOTAS_ID + ")" +
                ")";

        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikesMascota);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.T_MASCOTAS);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.T_MASCOTAS_LIKED);
        onCreate(db);
    }


    private  void delteLike(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ConstantesBaseDatos.T_MASCOTAS_LIKED_ORDINAL+" WHERE 1=1");
        db.close();
    }

    public void insertMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.T_MASCOTAS, null, contentValues);
        db.close();
    }

    public void insertLike(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.T_MASCOTAS_LIKED,null,contentValues);
        db.close();
    }


    public void darLike(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<Mascota> mascotas = new ArrayList<>(this.obtenerMascotasLike());
        ArrayList<Integer> likes = new ArrayList<>();
        if (mascotas.size() < 5){
            ContentValues cont = new ContentValues();
            cont.put(ConstantesBaseDatos.T_MASCOTAS_LIKED_ORDINAL,mascotas.size()+1);
            cont.put(ConstantesBaseDatos.T_MASCOTAS_LIEKD_ID_MASCOTA,id);
            this.insertLike(cont);
        }
        else{
            this.delteLike();
            ContentValues content = new ContentValues();
            content.put(ConstantesBaseDatos.T_MASCOTAS_LIKED_ORDINAL,1);
            content.put(ConstantesBaseDatos.T_MASCOTAS_LIEKD_ID_MASCOTA,id);
            this.insertLike(content);
            for (int i=0; i<4;i++){
                content = new ContentValues();
                content.put(ConstantesBaseDatos.T_MASCOTAS_LIKED_ORDINAL,i+2);
                content.put(ConstantesBaseDatos.T_MASCOTAS_LIEKD_ID_MASCOTA,mascotas.get(i).getId());
                this.insertLike(content);
            }

        }


    }


    public ArrayList<Mascota> obtenerMascotas(){

        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query  = "SELECT * FROM "+ConstantesBaseDatos.T_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor registros = db.rawQuery(query,null);

        while(registros.moveToNext()){
            Mascota actual = new Mascota(registros.getInt(0),registros.getString(1),registros.getInt(2));
            mascotas.add(actual);
        }

        return mascotas;
    }

    public ArrayList<Mascota> obtenerMascotasLike(){

        ArrayList<Mascota> mascotas = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        String query_liked  = "SELECT * FROM "+ConstantesBaseDatos.T_MASCOTAS_LIKED;


        Cursor registros_liked = db.rawQuery(query_liked,null);

        while (registros_liked.moveToNext()){
            Cursor mascota = db.rawQuery("SELECT * FROM "+ConstantesBaseDatos.T_MASCOTAS+" WHERE id = "+registros_liked.getInt(1),null);

            if (mascota.moveToNext()){
                Mascota mascota_liked  = new Mascota(mascota.getInt(0),mascota.getString(1), mascota.getInt(2));
                mascotas.add(mascota_liked);
            }

        }

        return mascotas;

    }


}
