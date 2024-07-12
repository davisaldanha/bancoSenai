import java.util.Scanner;

public class Main {

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            exibirMenu();
            int opcao = scan.nextInt();

            switch (opcao) {
                case 1:

                    break;
                case 2:
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
                new ContaBancaria(valor, senha));

        user.getContaBancaria().setUsuario(user);


    }

}

