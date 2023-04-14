/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf3m212.livrariapoo;

import controller.CCliente;
import controller.CEditora;
import controller.CLivro;
import controller.CVendaLivro;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import util.Validadores;
import java.util.Scanner;
import model.Cliente;
import model.Editora;
import model.Livro;
import model.VendaLivro;
import services.ClienteServicos;
import services.EditoraServicos;
import services.LivroServico;
import services.ServicosFactory;
import services.VendaLivroServicos;

/**
 *
 * @author jbferraz
 */
public class INF3M212LivrariaPOO {

    public static CCliente cadCliente = new CCliente();
    public static CEditora cadEditora = new CEditora();
    public static CLivro cadLivro = new CLivro();
    public static CVendaLivro cadVendaLivro = new CVendaLivro();
    public static Scanner leia = new Scanner(System.in);

    public static int leiaNumInt() {
        Scanner leiaNum = new Scanner(System.in);
        int num = 99;
        try {
            num = leiaNum.nextInt();
        } catch (Exception e) {
            System.out.println("Tente novamente!");
            leiaNum.nextLine();
        }
        return num;
    }

    public static float leiaNumFloat() {
        Scanner leiaNum = new Scanner(System.in);
        float num = 99;
        try {
            num = leiaNum.nextFloat();
        } catch (Exception e) {
            System.out.println("Tente novamente!");
            leiaNum.nextLine();
        }
        return num;
    }

    public static void menuP() {
        System.out.println(".: Livraria :.");
        System.out.println("1 - Ger. Clientes");
        System.out.println("2 - Ger. Editoras");
        System.out.println("3 - Ger. Livros");
        System.out.println("4 - Venda Livro");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void subMenu(int op) {
        String tpCad = null;
        switch (op) {
            case 1:
                tpCad = "Cliente";
                break;
            case 2:
                tpCad = "Editora";
                break;
            case 3:
                tpCad = "Livro";
                break;
        }
        System.out.println(".: Ger. " + tpCad + " :.");
        System.out.println("1 - Cadastrar " + tpCad);
        System.out.println("2 - Editar " + tpCad);
        System.out.println("3 - Listar " + tpCad + "s");
        System.out.println("4 - Deletar " + tpCad);
        System.out.println("0 - Voltar");
        System.out.print("Escolha uma opção: ");
    }

    public static void cadastrarCliente() {
        int idCliente;
        String nomeCliente;
        String cpf;
        String cnpj = null;
        String endereco;
        String telefone;
        ClienteServicos clienteS = ServicosFactory.getClienteServicos();

        System.out.println("-- Cadastro de Cliente --");
        System.out.print("Informe o CPF: ");
        boolean cpfIs;
        int opCPF;
        do {
            cpf = leia.nextLine();
            cpfIs = Validadores.isCPF(cpf);
            if (!cpfIs) {
                System.out.println("CPF inválido!"
                        + "\nDeseja tentar novamente? 1 - Sim | 2 - Não");
                opCPF = leiaNumInt();
                if (opCPF == 1) {
                    System.out.print("Informe o CPF: ");
                } else if (opCPF == 2) {
                    System.out.println("Cadastro cancelado pelo usuário!");
                    return;
                }
            }
        } while (!cpfIs);
        if (clienteS.buscarClientebyCPF(cpf).getCpf() != null) {
            System.out.println("Cliente já cadastrado!");
        } else {
            System.out.print("Informe o nome: ");
            nomeCliente = leia.nextLine().toUpperCase();
            System.out.print("Informe o telefone: ");
            telefone = leia.nextLine();
            System.out.print("Informe o endereço: ");
            endereco = leia.nextLine().toUpperCase();
            idCliente = cadCliente.geraID();
            Cliente cli = new Cliente(idCliente, nomeCliente, cpf,
                    cnpj, endereco, telefone);

            clienteS.cadCliente(cli);
            //cadCliente.addCliente(cli);
            System.out.println("Cliente cadastrado com sucesso!");
        }
    }//fim cadastrarCliente

    public static void deletarCliente() {
        System.out.println("-- Deletar Cliente --");
        System.out.print("Informe o cpf: ");
        String cpf = leia.nextLine();
        ClienteServicos clienteS = ServicosFactory.getClienteServicos();
        if (Validadores.isCPF(cpf)) {
            Cliente cli = clienteS.buscarClientebyCPF(cpf);
            if (cli != null) {
                //cadCliente.removeCliente(cli);
                clienteS.deletarCliente(cpf);
                System.out.println("Cliente deletado com sucesso!");
            } else {
                System.out.println("Cliente não consta na base de dados!");
            }
        } else {
            System.out.println("CPF inválido!");
        }
    }//fim deletarCliente

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        cadCliente.mockClientes();
        cadEditora.mockEditoras();
        cadLivro.mockLivros();
        cadVendaLivro.mockVendaLivros();
        int opM;

        do {
            menuP();
            opM = leiaNumInt();
            switch (opM) {
                case 1:
                case 2:
                case 3:
                    int opSM;
                    do {
                        subMenu(opM);
                        opSM = leiaNumInt();
                        switch (opSM) {
                            case 1:
                                System.out.println("-- Cadastrar --");
                                //usar opM pra definir qual cadastro
                                if (opM == 1) {
                                    cadastrarCliente();
                                } else if (opM == 2) {
                                    cadastrarEditora();
                                } else if (opM == 3) {
                                    cadastrarLivro();
                                }
                                break;
                            case 2:
                                System.out.println("-- Editar --");
                                if (opM == 1) {
                                    editarCliente();
                                } else if (opM == 2) {
                                    editarEditora();
                                } else if (opM == 3) {
                                    editarLivro();
                                }
                                break;
                            case 3:
                                System.out.println("-- Listar --");
                                if (opM == 1) {
                                    listarClientes();
                                } else if (opM == 2) {
                                    listarEditora();
                                } else if (opM == 3) {
                                    listarLivro();
                                }
                                break;
                            case 4:
                                System.out.println("-- Deletar --");
                                if (opM == 1) {
                                    deletarCliente();
                                } else if (opM == 2) {
                                    deletarEditora();
                                } else if (opM == 3) {
                                    deletarLivro();
                                }
                                break;
                            case 0:
                                System.out.println("-- Menu Principal --");
                                break;
                            default:
                                System.out.println("Opção inválida, tente novamente!");
                                break;
                        }
                    } while (opSM != 0);//fim subMenu
                    break;
                case 4:
                    System.out.println("-- Venda Livro --");
                    vendaLivro();
                    break;
                case 0:
                    System.out.println("Aplicação encerrada pelo usuário!");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente!");
                    break;
            }
        } while (opM != 0);//fim Sistema

    }

    private static void listarClientes() {
        ClienteServicos clienteS = ServicosFactory.getClienteServicos();
        for (Cliente cli : clienteS.getClientes()) {
            System.out.println("---");
            System.out.println("CPF:\t" + cli.getCpf());
            System.out.println("Nome:\t" + cli.getNomeCliente());
            System.out.println("Fone:\t" + cli.getTelefone());
        }
    }

    private static void editarCliente() {
        System.out.println("-- Editar Cliente --");
        System.out.print("Informe o CPF: ");
        String cpf = leia.nextLine();
        if (Validadores.isCPF(cpf)) {
            Cliente cli = cadCliente.getClienteCPF(cpf);
            if (cli != null) {
                System.out.println("1 - Nome:\t" + cli.getNomeCliente());
                System.out.println("2 - Endereço:\t" + cli.getEndereco());
                System.out.println("3 - Fone:\t" + cli.getTelefone());
                System.out.println("4 - Todos os campos acima?");
                System.out.print("Qual campo deseja alterar?"
                        + "\nDigite aqui: ");
                int opEditar = leiaNumInt();
                //Sugestão Sérgio INF3M212
                if (opEditar == 1 || opEditar == 4) {// "||" pipe significa "ou"
                    System.out.print("Informe o nome: ");
                    cli.setNomeCliente(leia.nextLine().toUpperCase());
                }
                if (opEditar == 2 || opEditar == 4) {
                    System.out.print("Informe o endereço: ");
                    cli.setEndereco(leia.nextLine().toUpperCase());
                }
                if (opEditar == 3 || opEditar == 4) {
                    System.out.print("Informe o telefone: ");
                    cli.setTelefone(leia.nextLine());
                }
                if (opEditar < 1 || opEditar > 4) {
                    System.out.println("Opção inválida!");
                }
                ClienteServicos clienteS = ServicosFactory.getClienteServicos();
                clienteS.atualizarCliente(cli);
                /*
                switch (opEditar) {
                    case 1:
                        System.out.print("Informe o nome: ");
                        cli.setNomeCliente(leia.nextLine());
                        break;
                    case 2:
                        System.out.print("Informe o endereço: ");
                        cli.setEndereco(leia.nextLine());
                        break;
                    case 3:
                        System.out.print("Informe o telefone: ");
                        cli.setTelefone(leia.nextLine());
                        break;
                    case 4:
                        System.out.println("Informe todos os campos abaixo:");
                        System.out.print("Informe o nome: ");
                        cli.setNomeCliente(leia.nextLine());
                        System.out.print("Informe o endereço: ");
                        cli.setEndereco(leia.nextLine());
                        System.out.print("Informe o telefone: ");
                        cli.setTelefone(leia.nextLine());
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
                 */
                System.out.println("Cliente:\n" + cli.toString());
            } else {
                System.out.println("Cliente não cadastrado!");
            }
        } else {
            System.out.println("CPF inválido!");
        }
    }

    public static void cadastrarEditora() {
        int idEditora;
        String nmEditora;
        String cnpj;
        String endereco;
        String telefone;
        String gerente;
        EditoraServicos editoraS = ServicosFactory.getEditoraServicos();

        System.out.println("-- Cadastrar Editora --");
        System.out.print("Informe o CNPJ da Editora: ");
        boolean cnpjis;
        int opCNPJ;
        do {
            cnpj = leia.nextLine();
            cnpjis = Validadores.isCNPJ(cnpj);
            if (!cnpjis) {
                System.out.print("CNPJ Invalido!"
                        + "\nDeseja tentar novamente? 1 - Sim || 2 - Não: ");
                opCNPJ = leiaNumInt();
                if (opCNPJ == 1) {
                    System.out.print("Informe o CNPJ: ");
                } else if (opCNPJ == 2) {
                    System.out.println("Cadastro encerrado pelo usuario!");
                    return;
                }
            }

        } while (!cnpjis);

        if (editoraS.buscarEditorabyCNPJ(cnpj).getCnpj() != null) {
            System.out.println("Editora já cadastrada!");
        } else {
            System.out.print("Informe o nome da editora: ");
            nmEditora = leia.nextLine().toUpperCase();
            System.out.print("Informe o telefone da editora: ");
            telefone = leia.nextLine();
            System.out.print("Informe o endereço da editora: ");
            endereco = leia.nextLine().toUpperCase();
            System.out.print("Informe o nome do gerente: ");
            gerente = leia.nextLine().toUpperCase();
            idEditora = cadEditora.geraID();

            Editora edi = new Editora(idEditora, nmEditora, cnpj, endereco, telefone, gerente);
            //cadEditora.addEditora(edi);
            editoraS.cadEditora(edi);
            System.out.println("Editora cadastrada com sucesso!");
        }
    }//fim do cadastrarEditora

    private static void editarEditora() {
        EditoraServicos editoraS = ServicosFactory.getEditoraServicos();
        System.out.println("-- Editar Editora --");
        System.out.print("Informe o CNPJ: ");
        String cnpj = leia.nextLine();
        if (Validadores.isCNPJ(cnpj)) {
            Editora edi = editoraS.buscarEditorabyCNPJ(cnpj);
            if (edi.getCnpj() != null) {
                System.out.println("1 - Nome:\t" + edi.getNmEditora());
                System.out.println("2 - Endereço:\t" + edi.getEndereco());
                System.out.println("3 - Fone:\t" + edi.getTelefone());
                System.out.println("4 - Todas as opções acima");
                System.out.print("Qual das opções deseja alterar? 1 || 2 || 3 || 4\n Digite aqui: ");
                int opEditar = leiaNumInt();
                switch (opEditar) {
                    case 1:
                        System.out.print("Informe o nome: ");
                        edi.setNmEditora(leia.nextLine());
                        break;
                    case 2:
                        System.out.print("Informe o endereço: ");
                        edi.setEndereco(leia.nextLine());
                        break;
                    case 3:
                        System.out.println("Informe o fone: ");
                        edi.setTelefone(leia.nextLine());
                        break;
                    case 4:
                        System.out.println("Informe todos os campos abaixo:");
                        System.out.print("Informe o nome: ");
                        edi.setNmEditora(leia.nextLine());
                        System.out.print("Informe o endereço: ");
                        edi.setEndereco(leia.nextLine());
                        System.out.print("Informe o fone: ");
                        edi.setTelefone(leia.nextLine());
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
                editoraS.atualizarEditora(edi);
                System.out.println("Editora:\n" + edi.toString());
            } else {
                System.out.println("Editora já cadastrado!");
            }
        } else {
            System.out.println("CNPJ inválido!");
        }
    }//fim do editarEditora

    public static void listarEditora() {
        EditoraServicos editoraS = ServicosFactory.getEditoraServicos();
        if (editoraS.getEditoras().isEmpty()) {
            System.out.println("Não existem Editoras cadastradas!");
        } else {
            for (Editora edi : editoraS.getEditoras()) {
                System.out.println("----");
                System.out.println("CNPJ:\t" + edi.getCnpj());
                System.out.println("Nome:\t" + edi.getNmEditora());
                System.out.println("Fone:\t" + edi.getTelefone());
            }

        }
    }//fim listarCliente

    public static void deletarEditora() {
        EditoraServicos editoraS = ServicosFactory.getEditoraServicos();
        System.out.println("-- Deletar Editora --");
        System.out.print("Informe o CNPJ: ");
        String cnpj = leia.nextLine();

        if (Validadores.isCNPJ(cnpj)) {
            Editora edi = editoraS.buscarEditorabyCNPJ(cnpj);
            if (edi != null) {
                //cadEditora.removeEditora(edi);
                editoraS.deletarEditora(cnpj);
                System.out.println("Editora deletada com sucesso!");
            } else {
                System.out.println("Editora não consta na base de dados!");
            }
        } else {
            System.out.println("CNPJ inválido!");
        }
    }//fim deletarEditora

    private static void cadastrarLivro() {
        LivroServico livroS = ServicosFactory.getLivroServico();
        EditoraServicos editoraS = ServicosFactory.getEditoraServicos();
        System.out.println("-- Cadastro de Livro --");
        System.out.print("Informe o ISBN: ");
        String isbn = leia.nextLine();
        if (livroS.buscarLivroISBN(isbn).getIsbn() != null) {
            System.out.println("Livro já cadastrado!");
        } else {
            //não precisa alterar para o servico do livro pois o id sera gerido pelo banco
            int idLivro = cadLivro.geraID();
            System.out.print("Informe o Titulo: ");
            String titulo = leia.nextLine().toUpperCase();
            System.out.print("Informe o autor: ");
            String autor = leia.nextLine().toUpperCase();
            System.out.print("Informe o assunto: ");
            String assunto = leia.nextLine().toUpperCase();
            System.out.print("Informe o estoque: ");
            int estoque = leiaNumInt();
            System.out.print("Informe o preço: ");
            float preco = leiaNumFloat();
            boolean isCNPJ = false;
            Editora idEditora = null;
            do {
                System.out.print("Informe o CNPJ da Editora: ");
                String cnpj = leia.nextLine();
                isCNPJ = Validadores.isCNPJ(cnpj);
                if (isCNPJ) {
                    idEditora = editoraS.buscarEditorabyCNPJ(cnpj);
                    if (idEditora == null) {
                        System.out.println("Editora não cadastrada!");
                        isCNPJ = false;
                    } else {
                        System.out.println("Editora: " + idEditora.getNmEditora());
                    }
                } else {
                    System.out.println("CNPJ inválido!");
                }
            } while (!isCNPJ);
            Livro li = new Livro(idLivro, titulo, autor, assunto, isbn, estoque, preco, idEditora);
            //cadLivro.addLivro(li);
            livroS.cadastrarLivro(li);
            System.out.println("Livro cadastrado com sucesso!");
        }
    }

    private static void editarLivro() {
        LivroServico livroS = ServicosFactory.getLivroServico();
        System.out.println("-- Editar Livro --");
        System.out.print("Informe o ISBN: ");
        String isbn = leia.nextLine();
        Livro li = livroS.buscarLivroISBN(isbn);
        if (li.getIsbn() != null) {
            System.out.println("Livro selecionado: " + li.getTitulo());
            System.out.println("O que deseja alterar:");
            System.out.println("1 - Estoque");
            System.out.println("2- Preço");
            System.out.println("3 - Todos acima");
            System.out.println("0 - Cancelar");
            System.out.print("Digite aqui: ");
            int op = leiaNumInt();
            
            if (op == 1 || op == 3) {
                System.out.println("Estoque atual:\t" + li.getEstoque());
                System.out.print("Informe novo estoque: ");
                li.setEstoque(leiaNumInt());
            }
            if (op == 2 || op == 3) {
                System.out.println("Preço atual:\t" + li.getPreco());
                System.out.print("Informe novo preço: ");
                li.setPreco(leiaNumFloat());
            }
            if (op == 0) {
                System.out.println("Operação cancelada pelo usuário!");
                return;
            }
            
            livroS.atualizarLivro(li);
            System.out.println("Livro Editado:");
            System.out.println(li.toString());
        } else {
            System.out.println("ISBN inválido!");
        }
    }

    private static void listarLivro() {
        System.out.println("-- Lista de Livros --");
        LivroServico livroS = ServicosFactory.getLivroServico();

        if (!livroS.buscarLivros().isEmpty()) {

            for (Livro livro : livroS.buscarLivros()) {
                /*System.out.println("---\nISBN:\t\t" + livro.getIsbn());
            System.out.println("Titulo:\t\t" + livro.getTitulo());
            System.out.println("Assunto:\t" + livro.getAssunto());
            System.out.println("Autor:\t\t" + livro.getAutor());
            System.out.println("Estoque:\t" + livro.getEstoque());
            System.out.println("Preço:\t\t" + livro.getPreco());
            System.out.println("Editora:\t" + livro.getIdEditora().getNmEditora());
                 */
                System.out.println(livro.toString());
            }
        } else {
            System.out.println("Não tenho livros cadastrados!");
        }
    }

    private static void deletarLivro() {
        LivroServico livroS = ServicosFactory.getLivroServico();
        System.out.println("-- Deletar Livro --");
        System.out.print("Infome o ISBN: ");
        String isbn = leia.nextLine();
        Livro li = livroS.buscarLivroISBN(isbn);
        if (li.getIsbn() != null) {
            System.out.println("Livro " + li.getTitulo() + " será deletado!");
            livroS.deletarLivro(isbn);
        } else {
            System.out.println("ISBN não encontrado!");
        }
    }

    private static void vendaLivro() {
        VendaLivroServicos vlS = ServicosFactory.getVendaLivros();
        LivroServico livroS = ServicosFactory.getLivroServico();
        ClienteServicos clienteS = ServicosFactory.getClienteServicos();
        
        
        int idVendaLivro;
        Cliente idCliente = null;
        ArrayList<Livro> livros = new ArrayList<>();
        float subTotal = 0;
        LocalDate dataVenda = LocalDate.now();

        do {//Seleciona Cliente
            System.out.print("Informe o CPF do Cliente: ");
            String CPF = leia.nextLine();
            if (Validadores.isCPF(CPF)) {
                idCliente = clienteS.buscarClientebyCPF(CPF);
                if (idCliente.getCpf() == null) {
                    System.out.println("Cliente não cadastrado, tente novamente!");
                }
            } else {
                System.out.println("CPF inválido, tente novamente!");
            }
        } while (idCliente.getCpf() == null);

        boolean venda = true;
        do {
            Livro li = null;
            String isbn;
            do {
                System.out.print("Informe o ISBN: ");
                isbn = leia.nextLine();
                li = livroS.buscarLivroISBN(isbn);
                if (li.getIsbn() == null) {
                    System.out.println("Livro não encontrado, tente novamente!");
                }
            } while (li.getIsbn() == null);
            if (li.getEstoque() > 0) {
                livros.add(li);
                //cadLivro.atualizaEstoqueLivro(li.getIsbn()); 
                int estoque = li.getEstoque() - 1;
                li.setEstoque(estoque);
                livroS.atualizarLivro(li);
                subTotal += li.getPreco();
            } else {
                System.out.println(li.getTitulo() + " não tem estoque");
            }
            System.out.println("Deseja comprar mais livros nesta venda?"
                    + "\n1 - Sim | 2 - Não"
                    + "\nDigite: ");
            if (leiaNumInt() == 2) {
                venda = false;
            }
        } while (venda);
        idVendaLivro = cadVendaLivro.geraID();
        VendaLivro vl = new VendaLivro(idVendaLivro, idCliente, livros, subTotal, dataVenda);
        //cadVendaLivro.addVendaLivro(vl);
        vlS.vendaLivros(vl);
        System.out.println("-- Venda --\n" + vl.toString());
    }

}
