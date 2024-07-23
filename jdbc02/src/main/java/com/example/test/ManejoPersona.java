package com.example.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.example.datos.Conexion;
import com.example.datos.PersonaDAO;
import com.example.domain.Persona;

public class ManejoPersona {
    public static void main(String[] args) {
        //PersonaDAO pDao = new PersonaDAO();

        // INSERTAR OBJETOS PERSONA trabajador

        // //insetar
        // Persona p1 = new Persona("Alexis", "Low", Date.valueOf("2020-02-20"),
        // "Ferr@gmail.com", "33321");

        // pDao.insertar(p1);

        // modificar
        // Persona pu1 = new Persona(4, "ee", "Low", Date.valueOf("2020-02-20"),
        // "Ferr@gmail.com", "33321");


        // Persona pu1 = new Persona();
        //     pu1.setId(20);
        //     pu1.setNombre("lose");
        //     pu1.setApellido("clame");
        //     pu1.setFecha(Date.valueOf("2020-01-24"));
        //     pu1.setCorreo("lame@mgg.com");
        //     pu1.setTelefono("0909");

        //     pDao.modificar(pu1);

        // pDao.modificar(pu1);
        // System.out.println(pDao.modificar(pu1));
        // //eliminar

        // Persona pd1 = new Persona(2);
        // pDao.eliminar(pd1);

        // List<Persona> personas = pDao.seleccionar();

        // for (Persona persona : personas) {
        // System.out.println(persona);
        // }

        // TRANSACCION
        Connection cnx =  null;
        try {
            cnx = Conexion.getConnectionn();
            if (cnx.getAutoCommit()) {
                cnx.setAutoCommit(false);
            }
            PersonaDAO pdao = new PersonaDAO(cnx);
            // obj

            // modificar
            // Persona pu1 = new Persona();
            // pu1.setId(20);
            // pu1.setNombre("lose");
            // pu1.setApellido("clame");
            // pu1.setFecha(Date.valueOf("2020-01-24"));
            // pu1.setCorreo("lame@mgg.com");
            // pu1.setTelefono("0909");

            // pdao.modificar(pu1);

            Persona pu1 = new Persona(6, "ee", "Low", Date.valueOf("2020-02-20"),
                    "Ferr@gmail.com", "33321");

            pdao.modificar(pu1);

            Persona ip1 = new Persona("nono", "nono", Date.valueOf("2023-02-20"),
                    "klame@gmail.com", "222");
            pdao.insertar(ip1);

            cnx.commit();
            System.out.println("Commit realizado");

            List<Persona> personas = pdao.seleccionar();

            for (Persona persona : personas) {
                System.out.println(persona);
            }

        } catch (SQLException e) {
            System.out.println("error para modificar");
            e.printStackTrace();
            try {
                cnx.rollback();
            } catch (SQLException e1) {
                
                e1.printStackTrace(System.out);
            }
        }
    }

}
