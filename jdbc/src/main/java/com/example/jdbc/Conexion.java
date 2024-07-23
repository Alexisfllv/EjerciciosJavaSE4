package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {


    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/objeto?useSSL=false&useTimezone=true&serverTimezon=UTC&allowPublicKeyRetrieval=true";
        String USER_MYSQL = "root";
        String PAS_MYSQL ="main";

        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection =  DriverManager.getConnection(url, USER_MYSQL, PAS_MYSQL);
            Statement instruccion =  connection.createStatement();
            var  sql = "SELECT * from trabajador";

            ResultSet res =  instruccion.executeQuery(sql);

            while (res.next()) {
                System.out.println(" {");
                System.out.println("id es : " + res.getString("id"));
                System.out.println("nombre es : " + res.getString("nombre"));
                System.out.println("apellido es : " + res.getString("apellido"));
                System.out.println("fecha es : " + res.getString("fecha_nacimiento"));
                System.out.println("correo es : " + res.getString("correo_electronico"));
                System.out.println("telefono es : " + res.getString("telefono"));
                System.out.println("}");
                //System.out.println(res);
            }

            res.close();
            instruccion.close();
            connection.close();
        }  catch(SQLException es){
            es.printStackTrace(System.out);
        }
    }
    

}
