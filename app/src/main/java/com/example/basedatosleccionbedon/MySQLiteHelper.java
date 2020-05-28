package com.example.basedatosleccionbedon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "comments.sqlite";
    private static final int DB_VERSION = 1;
    private SQLiteDatabase db;

    //Constructor
    public MySQLiteHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE comments(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, direccion TEXT)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Metodos CRUD
    public void insertar(String nombre, String direccion){
        ContentValues cv = new ContentValues();
        cv.put("nombre", nombre);
        cv.put("direccion", direccion);
        db.insert("comments",null,cv);
    }

    public void eliminar(int id){
        String[] argumentos = new String[]{String.valueOf(id)};
        db.delete("comments","id=?", argumentos);
    }

    //Obtener la lista de clientes guardados en la BD
    public ArrayList<Cliente> obtenerClientes(){
        ArrayList<Cliente> listaClientes = new ArrayList<>();

        //Cursor es un ResultSet conocido en Java.
        Cursor c = db.rawQuery("select id, nombre, direccion from comments",null);

        if(c != null && c.getCount()>0){
            c.moveToFirst();

            do {
                Integer id = c.getInt(c.getColumnIndex("id"));
                String nombre = c.getString(c.getColumnIndex("nombre"));
                String sDireccion = c.getString(c.getColumnIndex("direccion"));

                //Creo un objeto con la informacion leida en la BD
                Cliente oCliente = new Cliente(id,nombre,sDireccion);
                //Agrego el objeto a la lista
                listaClientes.add(oCliente);
            } while(c.moveToNext());
        }

        c.close();

        return listaClientes;
    }
}
