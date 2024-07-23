package com.example.Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.Domain.Usuario;


public class UsuarioDAO {


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
    public UsuarioDAO(){

    }

    //
    public UsuarioDAO(Connection connection){
        this.conexionTransaccional =  connection;
    }



    public List<Usuario> selects(){

        Connection cn = null;
        PreparedStatement pstm =  null;
        ResultSet rs =  null;
        Usuario us = null;

        List<Usuario> usuarios =  new ArrayList<>();

        try {
            cn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexxion.getConnecttion();
            pstm =  cn.prepareStatement(SQL_SELECT);
            rs =  pstm.executeQuery();


            while (rs.next()) {
                int id =  rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String password =  rs.getString("password");

                us =  new Usuario(id, nombre, password);
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

    public void insertar(Usuario usuario){

        Connection cn =  null;
        PreparedStatement pstm =  null;
        

        try {
            cn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexxion.getConnecttion();
            pstm =  cn.prepareStatement(SQL_INSERT);
            pstm.setString(1, usuario.getNombre());
            pstm.setString(2, usuario.getPassword());

            pstm.executeUpdate();
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
    }

    //md
    
    public void mds (Usuario usuario){
        Connection cn =  null;
        PreparedStatement pstm = null;
        //rgs
        try {
            cn = this.conexionTransaccional != null ? this.conexionTransaccional :  Conexxion.getConnecttion();
            pstm =  cn.prepareStatement(SQL_UPDATE);

            pstm.setString(1, usuario.getNombre());
            pstm.setString(2, usuario.getPassword());
            pstm.setInt(3, usuario.getId_usuario());
            pstm.executeUpdate();
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
    }

    public void eliminar (Usuario usuario){
        Connection cn =  null;
        PreparedStatement pStatement =  null;

        try {
            cn  = this.conexionTransaccional != null ? this.conexionTransaccional : Conexxion.getConnecttion();
            pStatement =  cn.prepareStatement(SQL_DELETE);

            //
            pStatement.setInt(1, usuario.getId_usuario());
            pStatement.executeUpdate();

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
    }


    //

    //


    
    public void retonrnarAccesoConexion(){
        this.conexionTransaccional =  conexionTransaccional;
    }


}
