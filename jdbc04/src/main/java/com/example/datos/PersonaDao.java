package com.example.datos;

import java.sql.SQLException;
import java.util.List;

import com.example.domain.PersonaDTO;

public interface PersonaDao {

    public List<PersonaDTO> selects()throws SQLException;

    public int insertar(PersonaDTO personaDTO) throws SQLException;

    public int mds(PersonaDTO personaDTO)throws SQLException;

    public int eliminar(PersonaDTO personaDTO) throws SQLException;

    
    
}