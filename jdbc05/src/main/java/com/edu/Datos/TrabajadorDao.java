package com.edu.Datos;

import java.sql.SQLException;
import java.util.List;

import com.edu.Domain.TrabajadorDTO;

public interface TrabajadorDao {


    //interfee para ejecutar en servicio

    public List<TrabajadorDTO> seleccionar()throws SQLException;

    public void insertar(TrabajadorDTO dto) throws SQLException;
    
    public void modificar(TrabajadorDTO dto) throws SQLException;

    public void eliminar(TrabajadorDTO dto) throws SQLException;
    
    
    
}