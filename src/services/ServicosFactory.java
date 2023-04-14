/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author 182020025
 */
public class ServicosFactory {

    private static ClienteServicos cServicos = new ClienteServicos();
    private static EditoraServicos eServicos = new EditoraServicos();
    private static LivroServico lServicos = new LivroServico();
    private static VendaLivroServicos vlS = new VendaLivroServicos();

    public static ClienteServicos getClienteServicos() {
        return cServicos;
    }

    public static EditoraServicos getEditoraServicos() {
        return eServicos;
    }

    public static LivroServico getLivroServico() {
        return lServicos;
    }
    public static VendaLivroServicos getVendaLivros() {
        return vlS;
    }
}
