/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.DAOFactory;
import dao.LivroDAO;
import java.util.ArrayList;
import model.Livro;

/**
 *
 * @author 182020025
 */
public class LivroServico {
    
    public void cadastrarLivro(Livro livroVO){
        LivroDAO livroDAO = DAOFactory.getLivroDAO();
        livroDAO.cadastrarLivroDAO(livroVO);
    }
    
    public ArrayList<Livro>buscarLivros(){
      LivroDAO livroDAO = DAOFactory.getLivroDAO(); 
      return livroDAO.getLivro();
    }
     public Livro buscarLivroISBN(String isbn){
      LivroDAO livroDAO = DAOFactory.getLivroDAO(); 
      return livroDAO.getLivrobyISBN(isbn);
    }
    public void deletarLivro(String isbn){
      LivroDAO livroDAO = DAOFactory.getLivroDAO();
      livroDAO.deletarLivroDAO(isbn);
    }
    
    public void atualizarLivro(Livro livroVO){
    LivroDAO livroDAO = DAOFactory.getLivroDAO(); 
    livroDAO.atualizarLivroDAO(livroVO);
    }
   
}
