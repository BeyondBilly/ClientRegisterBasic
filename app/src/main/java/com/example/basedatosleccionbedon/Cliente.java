package com.example.basedatosleccionbedon;

public class Cliente {
    //Campos correspondientes a la tabla de mi BD Sqlite
    int id;
    String nombre;
    String direccion;


    //Constructor
    public Cliente(int id, String nombre, String direccion){
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    //Metodos Get and Set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString(){
        return nombre;
    }
}
