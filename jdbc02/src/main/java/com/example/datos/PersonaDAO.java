package com.example.datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.domain.Persona;

public class PersonaDAO {

    //
    private Connection conexionTransaccional;

    private static final String SQL_SELECT = 
    "SELECT id,nombre,apellido,fecha_nacimiento , correo_electronico,telefono FROM trabajador";

    private static final String SQL_INSERT =
    "INSERT INTO trabajador (nombre,apellido,fecha_nacimiento,correo_electronico,telefono) VALUES (?,?,?,?,?)";

    private static final String SQL_UPDATE =
    "UPDATE trabajador SET nombre = ? ,apellido = ? ,fecha_nacimiento = ? ,  correo_electronico = ? , telefono = ? WHERE id = ?";
   
    private static final String SQL_DELETE =
    "DELETE FROM trabajador WHERE id = ?";
    
    //cns
    public PersonaDAO(){

    }

    public PersonaDAO(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }

    
    //
    public List<Persona> seleccionar() throws SQLException{
        Connection cn =  null;
        PreparedStatement pstm = null;
        ResultSet rs =  null;
        Persona per =  null;
        List<Persona> personas =  new ArrayList<>();

        //
        try {
            cn =  this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnectionn();
            pstm = cn.prepareStatement(SQL_SELECT);
            rs =  pstm.executeQuery();

            while (rs.next()) {
                int id =  rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Date fecha_nacimiento = rs.getDate("fecha_nacimiento");
                String correo_electronico = rs.getString("correo_electronico");
                String telefono = rs.getString("telefono");

                per = new Persona(id, nombre, apellido, fecha_nacimiento, correo_electronico, telefono);
                personas.add(per);
            }   
        } finally{
            try {
                //
                Conexion.close(rs);
                Conexion.close(pstm);
                

                if (this.conexionTransaccional ==null) {
                    Conexion.close(cn);
                }


            } catch (SQLException e) {
                System.out.println("error al cerrar los Conexion");
                e.printStackTrace(System.out);
                
            }
        }
        return personas;
    }

    

    //(nombre,apellido, fecha_nacimiento,correo_electronico,telefono)

    // insertar informacion

    public int insertar (Persona persona) throws SQLException{
        Connection cn =  null;
        PreparedStatement pstm = null;
        int registros = 0;

        //
        try {
            cn =  this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnectionn();
            pstm = cn.prepareStatement(SQL_INSERT);
            pstm.setString(1, persona.getNombre());
            pstm.setString(2, persona.getApellido());
            pstm.setDate(3, persona.getFecha());
            pstm.setString(4, persona.getCorreo());
            pstm.setString(5, persona.getTelefono());

            registros = pstm.executeUpdate();

        }finally{
            try {
                Conexion.close(pstm);
                

                if (this.conexionTransaccional ==null) {
                    Conexion.close(cn);
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int modificar(Persona persona) throws SQLException{
        Connection cn =  null;
        PreparedStatement pstm = null;
        int registros = 0;
        try {
            cn =  this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnectionn();

            pstm =  cn.prepareStatement(SQL_UPDATE);
            pstm.setString(1, persona.getNombre());
            pstm.setString(2, persona.getApellido());
            pstm.setDate(3, persona.getFecha());
            pstm.setString(4, persona.getCorreo());
            pstm.setString(5, persona.getTelefono());
            pstm.setInt(6, persona.getId());

            registros = pstm.executeUpdate();
        
        }finally{
            try {
                Conexion.close(pstm);
                if (this.conexionTransaccional ==null) {
                    Conexion.close(cn);
                }
            } catch (SQLException e) {
                //Abialbe
                e.printStackTrace(System.out);
            }
        }
        return registros;
        //
    }

    public int eliminar(Persona persona) throws SQLException{
        Connection cn =  null;
        PreparedStatement pstm = null;
        int registros = 0;

        try {
            cn =  this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnectionn();
            pstm =  cn.prepareStatement(SQL_DELETE);
            pstm.setInt(1, persona.getId());

            registros =  pstm.executeUpdate();
        }finally{
            try {
                Conexion.close(pstm);
                if (this.conexionTransaccional == null) {
                    Conexion.close(cn);
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return registros;
    }
}
