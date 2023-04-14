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
import model.Livro;
import services.EditoraServicos;
import services.ServicosFactory;

/**
 *
 * @author 182020025
 */
public class LivroDAO {

    public void cadastrarLivroDAO(Livro livroVO) {
        try {
            Connection con = Conexao.getConexao();
            String sql = "insert into livros values(null,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, livroVO.getTitulo());
            pst.setString(2, livroVO.getAutor());
            pst.setString(3, livroVO.getAssunto());
            pst.setString(4, livroVO.getIsbn());
            pst.setInt(5, livroVO.getEstoque());
            pst.setFloat(6, livroVO.getPreco());
            pst.setInt(7, livroVO.getIdEditora().getIdEditora());
            pst.execute();

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar livro\n" + e.getMessage());
        }
    }// Fim do metodo

    public ArrayList<Livro> getLivro() {
        ArrayList<Livro> livros = new ArrayList<>();
        try {
            Connection con = Conexao.getConexao();
            String sql = "select livros. *,e.cnpj from livros "
                    + "join editoras e using(ideditora)";

            EditoraServicos editoraS = ServicosFactory.getEditoraServicos();

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Livro li = new Livro();

                li.setIdLivro(rs.getInt("idlivro"));
                li.setTitulo(rs.getString("titulo"));
                li.setAssunto(rs.getString("assunto"));
                li.setAutor(rs.getString("autor"));
                li.setIsbn(rs.getString("isbn"));
                li.setEstoque(rs.getInt("estoque"));
                li.setPreco(rs.getFloat("preco"));
                li.setIdEditora(editoraS.buscarEditorabyCNPJ(rs.getString("cnpj")));
                livros.add(li);

            }
        } catch (SQLException e) {
            System.out.println("erro ao listar livros.\n" + e.getMessage());
        }

        return livros;
    }//fim getlivrosDAO

    public Livro getLivrobyISBN(String isbn) {
        Livro li = new Livro();
        EditoraServicos editoraS = ServicosFactory.getEditoraServicos();
        try {
            Connection con = Conexao.getConexao();
            String sql = "select livro.*, e.cnpj from livros join editoras e using(ideditora)where sbn =?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, isbn);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                li.setIdLivro(rs.getInt("idlivro"));
                li.setTitulo(rs.getString("titulo"));
                li.setAssunto(rs.getString("assunto"));
                li.setAutor(rs.getString("autor"));
                li.setIsbn(rs.getString("isbn"));
                li.setEstoque(rs.getInt("estoque"));
                li.setPreco(rs.getFloat("preco"));
                li.setIdEditora(editoraS.buscarEditorabyCNPJ(rs.getString("cnpj")));

            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar livro.\n" + e.getMessage());

        }
        return li;
    }

    public void atualizarLivroDAO(Livro livroVO) {
        try {
            Connection con = Conexao.getConexao();
            String sql = "update livros set estoque = ?, preco = ? where isbn =?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, livroVO.getIsbn());
            pst.setInt(2, livroVO.getEstoque());
            pst.setFloat(3, livroVO.getPreco());
            pst.execute();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar livro\n" + e.getMessage());
        }
    }// fim do metodo

    public void deletarLivroDAO(String isbn) {
        try {
            Connection con = Conexao.getConexao();
            String sql = "delete from livros where isbn =  = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, isbn);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar livro\n" + e.getMessage());
        }
    }//fim deletar livro
}
