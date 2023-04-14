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
import static javax.swing.UIManager.getInt;
import model.Livro;
import model.VendaLivro;

/**
 *
 * @author 182020025
 */
public class VandaLivroDAO {

    public void cadastrarVendaLivroDAO(VendaLivro vlVO) {
        try {
            Connection con = Conexao.getConexao();
            String sql = "insert into pedidos values(null,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            java.sql.Date dataAtual = java.sql.Date.valueOf(vlVO.getDataVenda());
            pst.setDate(1, dataAtual);
            pst.setInt(2, vlVO.getIdCliente().getIdCliente());
            pst.setFloat(3, vlVO.getSubTotal());
            pst.executeUpdate();

            //Pegar do BD o ultimo id inserido na tabela pedidos
            String sqlidPedido = "select max(idPedido) from pedidos";
            PreparedStatement pst2 = con.prepareStatement(sqlidPedido);
            ResultSet rsIdPed = pst2.executeQuery();
            int idPedido = 0;
            while (rsIdPed.next()) {
                idPedido = rsIdPed.getInt("idpedido");
            }
            
            String sqlPedLivros = "insert into pedidoslivros values (?,?)";
            PreparedStatement pst3 = con.prepareStatement(sqlPedLivros);
            for(Livro livro: vlVO.getLivros()){
                pst3.setInt(1, idPedido);
                pst3.setInt(2,livro.getIdLivro() );
                pst3.executeUpdate();
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao realizar venda!\n" + e.getMessage());
        }

    }

}
