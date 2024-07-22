import database.MyDatabase;
import model.ContaBancaria;
import model.Usuario;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static Scanner scan = new Scanner(System.in);
    private static MyDatabase database = new MyDatabase();
    private static Usuario userPrincipal;

    public static void main(String[] args) throws IOException {

        while (true) {
            exibirMenu();
            int opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                    cadastro();
                    break;
                case 2:
                    acessarConta();
                    break;
                case 0:
                    System.out.println("Até breve!");
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }

            if (opcao == 0)
                break;
        }
    }

    public static void exibirMenu() {
        System.out.println("---------------------");
        System.out.println("     BANCO SENAI     ");
        System.out.println("---------------------\n");

        System.out.println("Seja Bem-Vindo! Escolha uma opção...");

        System.out.println("1 - CADASTRAR CONTA BANCÁRIA");
        System.out.println("2 - ACESSAR CONTA BANCÁRIA");
        System.out.println("0 - SAIR");
        System.out.print("-> ");
    }

    public static void cadastro() {
        System.out.print("Nome do Usuário: ");
        String nome = scan.nextLine();

        System.out.print("Telefone: ");
        String telefone = scan.nextLine();

        System.out.print("CPF: ");
        String cpf = scan.nextLine();

        System.out.print("Endereço: ");
        String endereco = scan.nextLine();

        System.out.print("Número da conta: ");
        String numero = scan.nextLine();

        System.out.print("Senha da conta: ");
        String senha = scan.nextLine();

        System.out.print("Deseja realizar um depósito inicial? [SIM] ou [NÃO]\n->");
        String resultado = scan.nextLine();
        double valor = 0;
        if(resultado.equals("SIM")){
            System.out.print("Valor: R$ ");
            valor = scan.nextDouble();
        }

        Usuario user = new Usuario(nome, telefone,cpf, endereco,
                new ContaBancaria(numero, valor, senha));

        user.getContaBancaria().setUsuario(user);

        database.getDatabase().add(user);
    }

    public static boolean validarAcesso(String numero, String senha){
        Usuario user;
        boolean flagResult = false;

        for(Object obj : database.getDatabase()){
            user = Usuario.class.cast(obj);

            if(user.getContaBancaria().getNumero().equals(numero) &&
                    user.getContaBancaria().getSenha().equals(senha)){
                flagResult = true;
                userPrincipal = user;
            }
        }

        return flagResult;
    }

    public static void acessarConta() throws IOException {
        System.out.print("NÚMERO DA CONTA: ");
        String numero = scan.next();

        System.out.print("SENHA:");
        String senha = scan.next();

        if(validarAcesso(numero, senha)){
            menuPrincipal();
        }else{
            System.out.println("ERROR! CREDENCIAIS INVÁLIDAS!\n\n");
            getRead();
        }
    }

    public static void menuPrincipal() throws IOException {
        int opcao;
        double valor;
        do{
            System.out.println("---------------------");
            System.out.println("     BANCO SENAI     ");
            System.out.println("---------------------\n");

            System.out.printf("OLÁ %s, BEM-VINDO!\n\n", userPrincipal.getNome());

            System.out.printf("SALDO EM CONTA: %.2f\n\n", userPrincipal.getContaBancaria().getSaldo());

            System.out.println("1 - SACAR");
            System.out.println("2 - DEPOSITAR");
            System.out.println("0 - SAIR");
            System.out.print("-> ");

            opcao = scan.nextInt();

            switch (opcao){
                case 1:
                    System.out.print("\nDIGITE O VALOR PARA SAQUE: ");
                    valor = scan.nextDouble();
                    scan.nextLine();

                    System.out.println(
                            userPrincipal.getContaBancaria().sacar(valor) ? "SAQUE CONCLUÍDO\n" : "SAQUE INDISPONÍVEL\n"
                    );
                    getRead();
                    break;
                case 2:
                    System.out.print("\nDIGITE O VALOR PARA DEPÓSITO: ");
                    valor = scan.nextDouble();
                    scan.nextLine();

                    System.out.println(
                            userPrincipal.getContaBancaria().depositar(valor) ? "DEPÓSITO CONCLUÍDO\n" : "DEPÓSITO INDISPONÍVEL\n"
                    );
                    getRead();

                    break;
                case 0:
                    System.out.println("Desconectando da conta...\n\n");
                    getRead();
                    break;
                default:
                    System.out.print("Opção Inválida! ");
                    getRead();
            }

        }while(opcao != 0);

    }

    public static void getRead() throws IOException {
        System.out.println("Tecle ENTER para continuar...");
        System.in.read();
    }

}

