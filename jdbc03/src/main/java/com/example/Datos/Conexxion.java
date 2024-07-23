package com.example.Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Conexxion {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/objeto?useSSL=false&useTimezone=true&serverTimezon=UTC&allowPublicKeyRetrieval=true";
    private static final String USER_MYSQL = "root";
    private static final String PAS_MYSQL ="main";


    public static Connection getConnecttion() throws SQLException{
        return DriverManager.getConnection(JDBC_URL, USER_MYSQL, PAS_MYSQL);
    }

    public static void close(ResultSet rs ) throws SQLException{
        rs.close();
    }

    public static void close(PreparedStatement pstm) throws SQLException{
        pstm.close();
    }

    public static void close (Connection cn) throws SQLException{
        cn.close();
    }
}
