package view;

import controller.UsuarioController;
import model.Usuario;
import java.util.Scanner;

public class MenuView {
    private Scanner sc = new Scanner(System.in);
    private UsuarioController usuarioController = new UsuarioController();

    public void exibirMenuPrincipal() {
        int opcao;
        do {
            System.out.println("\n==== Biblioteca Pessoal ====");
            System.out.println("1 - Cadastrar novo usuário");
            System.out.println("2 - Fazer login");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {
                case 1 -> cadastrarUsuario();
                case 2 -> fazerLogin();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarUsuario() {
        System.out.println("\n--- Cadastro de Usuário ---");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("E-mail: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        boolean sucesso = usuarioController.cadastrar(nome, email, senha);
        if (sucesso) {
            System.out.println("Usuário cadastrado com sucesso!");
            System.out.println("Redirecionando para o login...");
            fazerLogin();
        } else {
            System.out.println("Erro ao cadastrar. Tente novamente.");
        }
    }

    private void fazerLogin() {
        System.out.println("\n--- Login ---");
        System.out.print("E-mail: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        Usuario usuario = usuarioController.login(email, senha);
        if (usuario != null) {
            System.out.println("\nLogin realizado com sucesso! Bem-vindo, " + usuario.getNome() + "!");
            menuLogado(usuario);
        } else {
            System.out.println("E-mail ou senha incorretos.");
        }
    }

    private void menuLogado(Usuario usuario) {
        int opcao;
        do {
            System.out.println("\n=== Menu do Usuário ===");
            System.out.println("1 - Cadastrar livro");
            System.out.println("2 - Listar livros");
            System.out.println("3 - Editar livro");
            System.out.println("4 - Remover livro");
            System.out.println("5 - Buscar por autor");
            System.out.println("6 - Buscar por categoria");
            System.out.println("7 - Avaliar livro");
            System.out.println("8 - Gerar relatório");
            System.out.println("0 - Logout");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 :
                    System.out.println("Essa funcionalidade será implementada em breve.");
                    break;
                case 2 :
                    System.out.println("Essa funcionalidade será implementada em breve.");
                    break;
                case 3 :
                    System.out.println("Essa funcionalidade será implementada em breve.");
                    break;
                case 4 :
                    System.out.println("Essa funcionalidade será implementada em breve.");
                    break;
                case 5 :
                    System.out.println("Essa funcionalidade será implementada em breve.");
                    break;
                case 6 :
                    System.out.println("Essa funcionalidade será implementada em breve.");
                    break;
                case 7 :
                    System.out.println("Essa funcionalidade será implementada em breve.");
                    break;
                case 8 :
                    System.out.println("Essa funcionalidade será implementada em breve.");
                    break;
                case 0 :
                    System.out.println("Saindo do menu do usuário...");
                    break;
                default :
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }
}
