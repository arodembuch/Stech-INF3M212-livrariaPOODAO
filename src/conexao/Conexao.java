/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author 182020025
 */
public class Conexao {
    //cria uma constante com endereço do BD

    private static String url = "jdbc:mysql://localhost:3306/livraria";
    //cria uma constante um user do BD
    private static String user = "root";
    //cria uma constante com a senha do BD
    private static String pass = "";

    public static Connection getConexao() throws SQLException {
        Connection c = null;
        //tentar estabelecer conecção
        try {
            c= DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            //caso haja erro na conexão do banco
            throw new SQLException("Erro ao conectar banco!\n" + e.getMessage());
        }
        
        return c;
        
    }
}
