package com.example.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.Datos.Conexxion;
import com.example.Datos.UsuarioDAO;
import com.example.Domain.Usuario;

public class ManejoUsuario {

    public static void main(String[] args) {

        // incializar el dao
        // UsuarioDAO uDao = new UsuarioDAO();


        // //ingresar nuevo usuario
        // Usuario su1 =  new Usuario("moch", "lol");

        // //uDao.insertar(su1);

        // //modificar

        // Usuario uu1 =  new Usuario(1, "lowli", "cambios");
        // //uDao.mds(uu1);

        // Usuario du1 =  new Usuario(1);
        // uDao.eliminar(du1);

        // //listado
        // List<Usuario> usuarios = uDao.selects();

        // for (Usuario usuario : usuarios) {
        //     System.out.println(usuario);
        // }

        // TRANSACCION
        Connection conx =  null;

        try {
            conx =  Conexxion.getConnecttion();

            if (conx.getAutoCommit()) {
                conx.setAutoCommit(false);
            }

            UsuarioDAO uDao = new UsuarioDAO(conx);

            //SQL

            Usuario updateUsuario1 =  new Usuario(4, "cambios", "documentos");
            uDao.mds(updateUsuario1);


            conx.commit();

            //Listado
            List<Usuario> usuarios =  uDao.selects();
            
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }


        } catch (SQLException e) {
            
            e.printStackTrace();

            //
            try {
                conx.rollback();
            } catch (SQLException e1) {
                
                e1.printStackTrace();
            }
        }

    }

}
