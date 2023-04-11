package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Editora;

/**
 *
 * @author jbferraz
 */
public class EditoraDAO {

    public void cadastrarEditoraDAO(Editora eVO) {
        try {
            //busca conexão com o BD
            Connection con = Conexao.getConexao();
            //cria espaço de trabalho SQl, é a área no Java onde
            //vamo executar os scripts SQL
            String sql;
            sql = "insert into editoras values (null, ?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, eVO.getNmEditora());
            pst.setString(2, eVO.getCnpj());
            pst.setString(3, eVO.getEndereco());
            pst.setString(4, eVO.getTelefone());
            pst.setString(5, eVO.getGerente());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar!\n"
                    + ex.getMessage());
        }
    }//fim cadastrar

    public ArrayList<Editora> getEditorasDAO() {
        ArrayList<Editora> editoras = new ArrayList<>();
        try {
            Connection con = Conexao.getConexao();
            Statement stat = con.createStatement();
            String sql = "select * from editoras";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                Editora e = new Editora();
                //lado do java |x| (lado do banco)
                e.setIdEditora(rs.getInt("ideditora"));
                e.setNmEditora(rs.getString("nomeEditora"));
                e.setCnpj(rs.getString("cnpj"));
                e.setEndereco(rs.getString("endereco"));
                e.setTelefone(rs.getString("telefone"));
                e.setGerente(rs.getString("gerente"));
                editoras.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao Listar!\n"
                    + ex.getMessage());
        }
        return editoras;
    }//fim do listar

    public Editora getEditoraByDoc(String cnpj) {
        Editora e = new Editora();
        try {
            Connection con = Conexao.getConexao();
            String sql = "select * from editoras where cnpj = ?;";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cnpj);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                //lado do java |x| (lado do banco)
                e.setIdEditora(rs.getInt("ideditora"));
                e.setNmEditora(rs.getString("nomeEditora"));
                e.setCnpj(rs.getString("cnpj"));
                e.setEndereco(rs.getString("endereco"));
                e.setTelefone(rs.getString("telefone"));
                e.setGerente(rs.getString("gerente"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar CNPJ!\n"
                    + ex.getMessage());
        }
        return e;
    }

    public void deletarEditoraDAO(String cnpj) {
        try {
            Connection con = Conexao.getConexao();
            String sql = "delete from editoras where cnpj = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cnpj);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar CNPJ!\n"
                    + ex.getMessage());
        }
    }//fim deletarEditora

    public void atualizaEditoraByDoc(Editora eVO) {
        try {
            Connection con = Conexao.getConexao();
            String sql = "update editoras set nomeEditora = ?, endereco = ?, telefone = ?, gerente = ? "
                    + "where cnpj = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, eVO.getNmEditora());
            pst.setString(5, eVO.getCnpj());
            pst.setString(2, eVO.getEndereco());
            pst.setString(3, eVO.getTelefone());
            pst.setString(4, eVO.getGerente());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar CNPJ!\n"
                    + ex.getMessage());
        }
    }
}
