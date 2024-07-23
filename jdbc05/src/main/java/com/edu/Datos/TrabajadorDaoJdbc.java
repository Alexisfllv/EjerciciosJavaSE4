package com.edu.Datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edu.Domain.TrabajadorDTO;

public class TrabajadorDaoJdbc implements TrabajadorDao {

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
    public TrabajadorDaoJdbc(){

    }

    public TrabajadorDaoJdbc(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }

    
    //
    public List<TrabajadorDTO> seleccionar() throws SQLException{
        Connection cn =  null;
        PreparedStatement pstm = null;
        ResultSet rs =  null;
        TrabajadorDTO per =  null;
        List<TrabajadorDTO> personas =  new ArrayList<>();

        //
        try {
            cn =  this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnecttion();
            pstm = cn.prepareStatement(SQL_SELECT);
            rs =  pstm.executeQuery();

            while (rs.next()) {
                int id =  rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Date fecha_nacimiento = rs.getDate("fecha_nacimiento");
                String correo_electronico = rs.getString("correo_electronico");
                String telefono = rs.getString("telefono");

                per = new TrabajadorDTO(id, nombre, apellido, fecha_nacimiento, correo_electronico, telefono);
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

    public void insertar (TrabajadorDTO persona) throws SQLException{
        Connection cn =  null;
        PreparedStatement pstm = null;
        

        //
        try {
            cn =  this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnecttion();
            pstm = cn.prepareStatement(SQL_INSERT);
            pstm.setString(1, persona.getNombre());
            pstm.setString(2, persona.getApellido());
            pstm.setDate(3, persona.getFecha());
            pstm.setString(4, persona.getCorreo());
            pstm.setString(5, persona.getTelefono());
            
            pstm.executeUpdate();

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
        
    }

    public void modificar(TrabajadorDTO persona) throws SQLException{
        Connection cn =  null;
        PreparedStatement pstm = null;
        
        try {
            cn =  this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnecttion();

            pstm =  cn.prepareStatement(SQL_UPDATE);
            pstm.setString(1, persona.getNombre());
            pstm.setString(2, persona.getApellido());
            pstm.setDate(3, persona.getFecha());
            pstm.setString(4, persona.getCorreo());
            pstm.setString(5, persona.getTelefono());
            pstm.setInt(6, persona.getId());

            pstm.executeUpdate();
        
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
        
        //
    }

    public void eliminar(TrabajadorDTO persona) throws SQLException{
        Connection cn =  null;
        PreparedStatement pstm = null;
        

        try {
            cn =  this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnecttion();
            pstm =  cn.prepareStatement(SQL_DELETE);
            pstm.setInt(1, persona.getId());

            pstm.executeUpdate();
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
        
    }

    
}