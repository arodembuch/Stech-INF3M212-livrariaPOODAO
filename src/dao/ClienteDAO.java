/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import model.Cliente;

/**
 *
 * @author 182020025
 */
public class ClienteDAO {

    public void cadastrarClienteDAO(Cliente cVO) {
        //busca conexao com banco de dados
        Connection con = Conexao.getConexao();
        try {
            //cria espaço de trabalho SQL, é a área no java onde
            //vamos executar os scripts SQL
            String sql;
            sql = "insert into clientes values(null,?,?,null,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cVO.getNomeCliente());
            pst.setString(2, cVO.getCpf());
            pst.setString(3, cVO.getEndereco());
            pst.setString(4, cVO.getTelefone());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar !\n"
                    + ex.getMessage());
        }
    }//fim do cadastrar

    public ArrayList<Cliente> getClienteBD() {
        Connection con = Conexao.getConexao();
        try {
            Statement stat = con.createStatement();
            String sql = "select* from clientes";
            ResultSet rs = stat.executeQuery(sql);
            ArrayList<Cliente> clientes = new ArrayList<>();
            while (rs.next()) {
                Cliente c = new Cliente();
                //lado do java|*| lado do banco  ( lado do banco)
                c.setIdCliente(rs.getInt("idcliente"));
                c.setNomeCliente(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setEndereco(rs.getString("endereco"));
                c.setTelefone(rs.getString("telefone"));
                clientes.add(c);
            }
            return clientes;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar!\n"
                    + ex.getMessage());
        }
        return null;
    }//fim do listar

    public Cliente getClienteByDoc(String cpf) {
        Connection con = Conexao.getConexao();
        Cliente c = null;
        try {

            String sql = "select*from clientes where cpf = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cpf);
            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {

                //lado do java|*| lado do banco  ( lado do banco)
                c.setIdCliente(rs.getInt("idcliente"));
                c.setNomeCliente(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setEndereco(rs.getString("endereco"));
                c.setTelefone(rs.getString("telefone"));
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao consultar CPF\n"
                    + ex.getMessage());

        }
        return c;
    }

    public void deletarClienteDAO(String cpf) {
        Connection con = Conexao.getConexao();

        try {
            String sql = "delete from clientes where cpf = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cpf);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar CPF\n"
                    + ex.getMessage());
        }
    }

    public void atualizaClienteByDoc(Cliente cVO) {
        Connection con = Conexao.getConexao();
        try {
            String sql = "update set nome = ?, endereco = ?, telefone = ? "
                    + "where cpf = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cVO.getNomeCliente());
            pst.setString(2, cVO.getEndereco());
            pst.setString(3, cVO.getTelefone());
            pst.setString(4, cVO.getCpf());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erro ao Atualizar CPF!\n");

        }
    }

}
