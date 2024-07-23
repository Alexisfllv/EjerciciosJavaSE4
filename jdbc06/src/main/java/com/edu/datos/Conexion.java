package com.edu.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/objeto?useSSL=false&useTimezone=true&serverTimezon=UTC&allowPublicKeyRetrieval=true";
    private static final String USER_MYSQL = "root";
    private static final String PAS_MYSQL = "main";

    //

    public static BasicDataSource dataSource;

    public static DataSource getDataSource() {

        if (dataSource == null) {
            dataSource= new BasicDataSource();
            dataSource.setUrl(JDBC_URL);
            dataSource.setUsername(USER_MYSQL);
            dataSource.setPassword(PAS_MYSQL);

            // Definimos el tamano inical del pool de conexiones

            dataSource.setInitialSize(5);
        }

        return dataSource;
    }

    public static Connection getConnecttion() throws SQLException {
        return getDataSource().getConnection();
    }

    public static void close(ResultSet rs) throws SQLException {
        rs.close();
    }

    public static void close(PreparedStatement pstm) throws SQLException {
        pstm.close();
    }

    public static void close(Connection cn) throws SQLException {
        cn.close();
    }
}
