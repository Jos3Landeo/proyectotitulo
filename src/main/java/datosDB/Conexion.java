/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datosDB;
import java.sql.*;

public class Conexion {
    private static final String JDBC_CLASS = "oracle.jdbc.OracleDriver";
    private static final String JDBC_URL = "jdbc:oracle:thin:@//peligros820.com:1521/orclpdb";
    private static final String JDBC_USER = "Prueba";
    private static final String JDBC_PASSWORD = "123";
    private static Connection conn = null;
    
    public static Connection getConexion(){
        try{
            Class.forName(JDBC_CLASS);
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            conn.setAutoCommit(false);
            if (conn != null) {
                System.out.println("Conexion exitosa");
            }
            else{
                System.out.println("Conexion Mala");
            }
        } catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        return conn;
    }
    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement stmt) {
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    /*
    public static void main(String[] args){
        Conexion c = new Conexion();
        c.getConexion();
    }*/
}
