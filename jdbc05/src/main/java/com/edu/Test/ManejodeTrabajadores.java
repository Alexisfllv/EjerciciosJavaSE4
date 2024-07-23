package com.edu.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edu.Datos.Conexion;
import com.edu.Datos.TrabajadorDao;
import com.edu.Datos.TrabajadorDaoJdbc;
import com.edu.Domain.TrabajadorDTO;

public class ManejodeTrabajadores {

    public static void main(String[] args)  {
        

        //



        //TrabajadorDao daoJdbc =  new TrabajadorDaoJdbc();
    
        // List<TrabajadorDTO> trabajadores =  daoJdbc.seleccionar();

        // for (TrabajadorDTO trabajadorDTO : trabajadores) {
        //     System.out.println(trabajadorDTO);
        // }

        //Transaccion
        Connection conx = null;

        try {
            conx =  Conexion.getConnecttion();

            if (conx.getAutoCommit()) {
                conx.setAutoCommit(false);
            }

            TrabajadorDao dao = new TrabajadorDaoJdbc();

            //
            List<TrabajadorDTO> trabajadores = dao.seleccionar();

            for (TrabajadorDTO trabajadorDTO : trabajadores) {
                System.out.println(trabajadorDTO);
            }

            //
            conx.commit();
            System.out.println("commit realizado");
            //
        } catch (SQLException e) {
            
            e.printStackTrace();

            
            try {
                conx.rollback();
            } catch (SQLException e1) {
                
                e1.printStackTrace(System.out);
            }
        }

        
        

    }
    
}
