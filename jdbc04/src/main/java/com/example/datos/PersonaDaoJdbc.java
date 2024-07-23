package com.example.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.domain.PersonaDTO;
import com.mysql.cj.xdevapi.PreparableStatement;

public class PersonaDaoJdbc implements PersonaDao{


    private Connection conexionTransaccional;

    private static final String SQL_SELECT =
    "SELECT * FROM usuario";

    private static final String SQL_INSERT =
    "INSERT INTO usuario (nombre,password) VALUES (?,?)";

    private static final String SQL_UPDATE =
    "UPDATE usuario SET nombre = ? , password = ? WHERE id_usuario = ?";

    private static final String SQL_DELETE =
    "DELETE FROM usuario WHERE id_usuario  = ?";

    //
    public PersonaDaoJdbc(){

    }

    //
    public PersonaDaoJdbc(Connection connection){
        this.conexionTransaccional =  connection;
    }



    public List<PersonaDTO> selects(){

        Connection cn = null;
        PreparedStatement pstm =  null;
        ResultSet rs =  null;
        PersonaDTO us = null;

        
        List<PersonaDTO> usuarios =  new ArrayList<>();

        try {
            cn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexxion.getConnecttion();
            pstm =  cn.prepareStatement(SQL_SELECT);
            rs =  pstm.executeQuery();


            while (rs.next()) {
                int id =  rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String password =  rs.getString("password");

                us =  new PersonaDTO(id, nombre, password);
                usuarios.add(us);
            }

        } catch (SQLException e) {
           
            e.printStackTrace();
        } finally{
            try {
                Conexxion.close(rs);
                Conexxion.close(pstm);
                if (this.conexionTransaccional == null) {
                    Conexxion.close(cn);
                }
            } catch (SQLException e) {
                
                e.printStackTrace();
            }
        }

        return usuarios;
    }

    public int insertar(PersonaDTO usuario){

        Connection cn =  null;
        PreparedStatement pstm =  null;
        int rows  = 0;

        try {
            cn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexxion.getConnecttion();
            pstm =  cn.prepareStatement(SQL_INSERT);
            pstm.setString(1, usuario.getNombre());
            pstm.setString(2, usuario.getPassword());

            rows = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally{
            try {
                Conexxion.close(pstm);
                if (this.conexionTransaccional == null) {
                    Conexxion.close(cn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 

        return rows;
    }

    //md
    
    public int mds (PersonaDTO usuario){
        Connection cn =  null;
        PreparedStatement pstm = null;
        int rows = 0;
        //rgs
        try {
            cn = this.conexionTransaccional != null ? this.conexionTransaccional :  Conexxion.getConnecttion();
            pstm =  cn.prepareStatement(SQL_UPDATE);

            pstm.setString(1, usuario.getNombre());
            pstm.setString(2, usuario.getPassword());
            pstm.setInt(3, usuario.getId_usuario());
            rows = pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Alls args");
            e.printStackTrace(System.out);
        } finally{
            try {
                Conexxion.close(pstm);
                if (this.conexionTransaccional == null) {
                    Conexxion.close(cn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return rows;
    }

    public int eliminar (PersonaDTO usuario){
        Connection cn =  null;
        PreparedStatement pStatement =  null;
        int rows = 0;

        try {
            cn  = this.conexionTransaccional != null ? this.conexionTransaccional : Conexxion.getConnecttion();
            pStatement =  cn.prepareStatement(SQL_DELETE);

            //
            pStatement.setInt(1, usuario.getId_usuario());
            rows = pStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                Conexxion.close(pStatement);
                if (this.conexionTransaccional == null) {
                    Conexxion.close(cn);
                }
            } catch (SQLException e) {
                
                e.printStackTrace();
            }
        }

        return rows;
    }


    //

    //


    
    public void retonrnarAccesoConexion(){
        this.conexionTransaccional =  conexionTransaccional;
    }

    


}
