package com.edu.Domain;

import java.sql.Date;

public class TrabajadorDTO {

    private int id;
    private String nombre;
    private String apellido;
    private Date fecha;
    private String correo;
    private String telefono;

    
    public TrabajadorDTO() {
    }


    public TrabajadorDTO(int id ) {
        this.id = id;
        
    }

    public TrabajadorDTO(String nombre, String apellido, Date fecha, String correo, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
        this.correo = correo;
        this.telefono = telefono;
    }

    public TrabajadorDTO(int id, String nombre, String apellido, Date fecha, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
        this.correo = correo;
        this.telefono = telefono;
    }


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


    public String getApellido() {
        return apellido;
    }


    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public Date getFecha() {
        return fecha;
    }


    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public String getCorreo() {
        return correo;
    }


    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public String getTelefono() {
        return telefono;
    }


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    @Override
    public String toString() {
        return "Persona [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fecha=" + fecha + ", correo="
                + correo + ", telefono=" + telefono + "]";
    }

    //
    
}
