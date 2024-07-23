package com.example.Domain;

public class Usuario {

    protected int id_usuario;
    protected String nombre;
    protected String password;

    //

    public Usuario(){

    }

    public Usuario(int id_usuario, String nombre, String password) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.password = password;
    }

    //
    public Usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    //
    public Usuario( String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario [id_usuario=" + id_usuario + ", nombre=" + nombre + ", password=" + password + "]";
    }
    

    
}
