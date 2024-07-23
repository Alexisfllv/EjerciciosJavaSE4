package com.example.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.datos.*;
import com.example.domain.PersonaDTO;


public class ManejoUsuario {

    public static void main(String[] args) {
        // TRANSACCION
        Connection conx =  null;
        try {
            conx =  Conexxion.getConnecttion();

            if (conx.getAutoCommit()) {
                conx.setAutoCommit(false);
            }
            PersonaDao pDao = new PersonaDaoJdbc(conx);

            PersonaDTO agregarPersona1 =  new PersonaDTO("nova", "sol");
            pDao.insertar(agregarPersona1);
            conx.commit();

            //Listado
            List<PersonaDTO> usuarios =  pDao.selects();
            
            for (PersonaDTO personaDTO : usuarios) {
                System.out.println(personaDTO);
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
            System.out.println("Etranmos al roollback");
            //
            try {
                conx.rollback();
            } catch (SQLException e1) {
                
                e1.printStackTrace();
            }
        }

    }

}
