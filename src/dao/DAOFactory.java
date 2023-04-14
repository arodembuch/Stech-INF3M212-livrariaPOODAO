/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author 182020025
 */
public class DAOFactory {
    private static ClienteDAO cDAO = new ClienteDAO();
    private static EditoraDAO eDAO = new EditoraDAO();
    private static LivroDAO livroDAO = new LivroDAO();
    private static VandaLivroDAO vlDAO = new VandaLivroDAO();
    
    public static ClienteDAO getClienteDAO(){
        return cDAO;
    }
    public static EditoraDAO getEditoraDAO(){
        return eDAO;
    }
    public static LivroDAO getLivroDAO(){
        return livroDAO;
    }
    public static VandaLivroDAO getVendaLivroDAO(){
        return vlDAO;
    } 
}
