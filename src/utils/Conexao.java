package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    public Connection getConnection() {
        try {
            return (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/BDCLIENTE","root","12345");
        } catch(SQLException erro) {
            throw new RuntimeException(erro);
        }
    } 
}
