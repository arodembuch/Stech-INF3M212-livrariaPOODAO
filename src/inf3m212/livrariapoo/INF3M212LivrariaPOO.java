/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf3m212.livrariapoo;

import JavaPackage.Validadores;
import controller.CCliente;
import controller.CEditora;
import controller.Clivro;
import controller.CVendaLivro;
import java.util.Scanner;
import model.Cliente;
import model.Editora;
import services.ClienteServicos;
import services.ServicosFactory;

/**
 *
 * @author jbferraz
 */
public class INF3M212LivrariaPOO {

    public static CCliente cadCliente = new CCliente();
    public static CEditora cadEditora = new CEditora();
    public static Clivro cadLivro = new Clivro();
    public static CVendaLivro cadCVendaLivro = new CVendaLivro();
    public static Scanner leia = new Scanner(System.in);

    public static int leiaNumInt() {
        Scanner leiaNum = new Scanner(System.in);
        int num = 99;
        try {
            return leiaNum.nextInt();
        } catch (Exception e) {
            System.out.println("Tente Novamente");
            leiaNumInt();

        }
        return num;
    }

    public static float leiaNumFloat() {
        Scanner leiaNum = new Scanner(System.in);
        float num = 99;
        try {
            return leiaNum.nextFloat();
        } catch (Exception e) {
            System.out.println("Tente Novamente");
            leiaNumFloat();
        }
        return num;
    }

    public static void menuP() {
        System.out.println("..: Livraria :..");
        System.out.println("1 - Ger Clientes");
        System.out.println("2 - Ger Editoras");
        System.out.println("3 - Ger Livros");
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
        System.out.println("Ger. " + tpCad);
        System.out.println("1 - Cadastrar " + tpCad);
        System.out.println("2 - Editar " + tpCad);
        System.out.println("3 - Listar " + tpCad + "s");
        System.out.println("4 - Deletar " + tpCad);
        System.out.println("0 - Voltar ");
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
        System.out.println("cadastro de cliente");
        System.out.println("Informe CPF");
        boolean cpfIs;
        int opCPF;
        do {

            cpf = leia.nextLine();
            cpfIs = Validadores.isCPF(cpf);

            if (!cpfIs) {
                System.out.println("CPF invalido \n deseja tentar novamente? 1- Sim |2 - Não");
                opCPF = leiaNumInt();
                if (opCPF == 1) {
                    System.out.println("Informe o CPF: ");
                } else if (opCPF == 2) {
                    System.out.println("Cadastro cancelado pelo usuário!");
                    break;

                }
            } else if (clienteS.buscarClientebyCPF(cpf) != null) {
                System.out.println("Cliente ja cadastrado");
            } else {
                System.out.println("Informe o nome:");
                nomeCliente = leia.nextLine();
                System.out.println("Informe telefone: ");
                telefone = leia.nextLine();
                System.out.println("Informe o endereço: ");
                endereco = leia.nextLine();
                idCliente = cadCliente.geraID();
                Cliente cli = new Cliente(idCliente, nomeCliente, cpf, cnpj, endereco, telefone);

                clienteS.cadCliente(cli);

                cadCliente.addCliente(cli);
                System.out.println("Cliente cadastrado com sucesso!");

            }

        } while (!cpfIs);
    }

    public static void listarClientes() {
        ClienteServicos clienteS = ServicosFactory.getClienteServicos();
        for (Cliente cli : clienteS.getClientes()) {

            System.out.println("---");
            System.out.println("Cpf:\t" + cli.getCpf());
            System.out.println("Nme:\t" + cli.getNomeCliente());
            System.out.println("fone\t" + cli.getTelefone());

        }
    }

    public static void deletarCliente() {
        System.out.println("Deletar cliente");
        System.out.println("Informe o cpf: ");
        String cpf = leia.next();
        if (Validadores.isCPF(cpf)) {
            Cliente cli = cadCliente.getClienteCPF(cpf);
            if (cli != null) {
                ClienteServicos clienteS = ServicosFactory.getClienteServicos();
                clienteS.deletarCliente(cpf);
                System.out.println("Cliente delletado com sucesso!");

            } else {
                System.out.println("cliente não consta na base de dados!");
            }

        } else {
            System.out.println("CPF invalido!");
        }

        for (Cliente cli : cadCliente.getClientes()) {
            System.out.println("---");
            System.out.println("Cpf:\t" + cli.getCpf());
            System.out.println("Nme:\t" + cli.getNomeCliente());
            System.out.println("fone\t" + cli.getTelefone());

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        cadCliente.mockClientes();
        cadEditora.mockEditoras();
        cadLivro.mockLivros();
        cadCVendaLivro.mockVendaLivros();
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
                                System.out.println("--cadastra--");
                                cadastrarCliente();
                                break;
                            case 2:
                                System.out.println("--Editar--");

                                editarCliente();
                                break;
                            case 3:
                                System.out.println("--Listar--");
                                listarClientes();
                                break;
                            case 4:
                                System.out.println("--Deletar--");
                                deletarCliente();
                                break;
                            case 0:
                                System.out.println("--Menu Principal--");
                                break;
                            default:
                                System.out.println("Opção invalida,tente novamente!");
                                break;
                        }
                    } while (opSM != 0);
                    break;
                case 4:
                    System.out.println("Venda Livro");
                    break;
                case 0:
                    System.out.println("aplicação encerrada pelo usuário");
                default:
                    System.out.println("Opção invalida ");
                    break;
            }
        } while (opM != 0);
    }

    private static void editarCliente() {
        System.out.println("--editar Cliente--");
        System.out.println("Informe o cpf: ");
        String cpf = leia.nextLine();
        if (Validadores.isCPF(cpf)) {
            Cliente cli = cadCliente.getClienteCPF(cpf);
            if (cli != null) {
                System.out.println("1-Nome:\t " + cli.getNomeCliente());
                System.out.println("2-Endereço:\t " + cli.getEndereco());
                System.out.println("3-fone:\t " + cli.getTelefone());
                System.out.println("4-todos os campos acima?");
                System.out.println("Qual campo deseja alterar? \n Digite aqui: ");
                int opEditar = leiaNumInt();
                if (opEditar == 1 || opEditar == 4) {
                    System.out.println("Informe o nome: ");
                    cli.setNomeCliente(leia.nextLine());
                }
                if (opEditar == 2 || opEditar == 4) {
                    System.out.println("Informe o endereço: ");
                    cli.setEndereco(leia.nextLine());
                }
                if (opEditar == 3 || opEditar == 4) {
                    System.out.println("Informe o telefone:");
                    cli.setTelefone(leia.nextLine());

                    if (opEditar < 1 || opEditar > 4) {
                        System.out.println("Opção invalida");
                    }

                    ClienteServicos clienteS = ServicosFactory.getClienteServicos();
                    clienteS.atualizarCliente(cli);
                }
                /*
                switch (opEditar) {
                    case 1:
                        System.out.println("Informe o nome: ");
                        cli.setNomeCliente(leia.nextLine());
                        break;
                    case 2:
                        System.out.println("Informe o endereço: ");
                        cli.setEndereco(leia.nextLine());
                        break;
                    case 3:
                        System.out.println("Informe o telefone:");
                        cli.setTelefone(leia.nextLine());
                        break;
                    case 4:
                        System.out.println("Informe todos os campos abaixo:");
                        System.out.println("Informe o nome: ");
                        cli.setNomeCliente(leia.nextLine());
                        System.out.println("Informe o endereço: ");
                        cli.setEndereco(leia.nextLine());
                        System.out.println("Informe o telefone:");
                        cli.setTelefone(leia.nextLine());
                        break;
                    default:
                        System.out.println("Opção invalida!");
                }
                 */
                System.out.println("Cliente:\n");
            } else {
                System.out.println("Cliente não cadastrado!");
            }
        } else {
            System.out.println("CPF invalido!");
        }

    }

    public static void cadastrarEditora() {
        int idEditora;
        String nmEditora;
        String cnpj;
        String endereco;
        String telefone;
        String gerente;

        System.out.println("cadastro de cliente");
        System.out.println("Informe CNPJ");
        boolean cnpjis;
        int opCnpj;
        do {
            cnpj = leia.nextLine();
            cnpjis = Validadores.isCNPJ(cnpj);
        } while (!Validadores.isCNPJ(cnpj));
        if (!cnpjis) {
            System.out.println("Cnpj invalido +\n deseja tentar novamente? 1- Sim |2 - Não");
            opCnpj = leiaNumInt();
            if (opCnpj == 1) {
                System.out.println("Informe o Cnpj: ");
            } else if (opCnpj == 2) {
                System.out.println("Cadastro cancelado pelo usuário!");

            }
        }
        while (!cnpjis);
        if (cadEditora.getEditoraCNPJ(cnpj) != null) {
            System.out.println("Editora  ja cadastrado");
        } else {
            System.out.println("Informe o nome da Editora:");
            nmEditora = leia.nextLine();
            System.out.println("Informe telefone: ");
            telefone = leia.nextLine();
            System.out.println("Informe o endereço: ");
            endereco = leia.nextLine();
            idEditora = cadEditora.geraID();
            System.out.println("informe nome do gerente:");
            gerente = leia.nextLine();
            Editora ed = new Editora(idEditora, nmEditora, cnpj, endereco, telefone, gerente);
            cadEditora.addEditora(ed);
            System.out.println("Editora cadastrada com sucesso!");
        }
    }

    public static void listarEditora() {
        for (Editora ed : cadEditora.getEditoras()) {
            System.out.println("---");
            System.out.println("Cnpj:\t" + ed.getCnpj());
            System.out.println("Nme:\t" + ed.getNmEditora());
            System.out.println("fone\t" + ed.getTelefone());

        }
    }

    public static void deletarEditora() {
        System.out.println("Deletar editora");
        System.out.println("Informe o cnpj: ");
        String cnpj = leia.next();
        if (Validadores.isCPF(cnpj)) {
            Editora edi = cadEditora.getEditoraCNPJ(cnpj);
            if (edi != null) {
                cadEditora.removeEditora(edi);
                System.out.println("Cliente delletado com sucesso!");

            } else {
                System.out.println("cliente não consta na base de dados!");
            }

        } else {
            System.out.println("CPF invalido!");
        }

        for (Editora edi : cadEditora.getEditoras()) {
            System.out.println("---");
            System.out.println("cnpj:\t" + edi.getCnpj());
            System.out.println("Nme:\t" + edi.getNmEditora());
            System.out.println("fone\t" + edi.getTelefone());

        }
    }

}
