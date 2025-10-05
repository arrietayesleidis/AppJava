/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author inarvaez
 */
public class Conector {
    
    private static final String URL="jdbc:mysql://127.0.0.1:3306/app_java";
    private static final String USER="root";
    private static final String PASSWORD="";
    
    private Connection conexion;
    
    
    public Connection conectar(){
    
        try{
            conexion = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Conexion exitosa");
            
        }catch(SQLException e){
            System.out.println("Error de conexion" +e.getMessage());
        }
        
        return conexion;
    }
    
    //Metodo para preparar sentencias SQL 
    public PreparedStatement prepararStatement(String sql) throws SQLException{
        
        Connection conn = conectar();
        return conn.prepareStatement(sql);
    }
    
    
    //Metodo para ejecutar una consulta que devuelve un ResultSet
    
    public ResultSet ejecutarConsulta(PreparedStatement ps) throws SQLException{
    
        return ps.executeQuery();
    
    }
    
    //Metodo para ejecutar (INSERT, UPDATE, DELETE)
    
    public int ejecutarUpdate(PreparedStatement ps) throws SQLException{
    
        return ps.executeUpdate();
    }
    
    public void desconectar(){
        try{
            if(conexion !=null && !conexion.isClosed()){
                conexion.close();
                System.out.println("Conexion cerrada");
            }
        }catch(SQLException e){
            System.out.println("Error al desconectarse"+ e.getMessage());
        }
    
    }
}