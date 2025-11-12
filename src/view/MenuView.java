package view;

import controller.UsuarioController;
import controller.LivroController;
import model.Usuario;
import model.Livro;
import java.util.List;
import java.util.Scanner;

public class MenuView {
    private Scanner sc = new Scanner(System.in);
    private UsuarioController usuarioController = new UsuarioController();
    private LivroController livroController = new LivroController();

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

    // RF01 - Cadastro de Usuários
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

    // RF02 - Login de Usuários
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
                case 1 -> cadastrarLivro(usuario);
                case 2 -> listarLivros(usuario);
                case 3 -> System.out.println("Essa funcionalidade será implementada em breve.");
                case 4 -> System.out.println("Essa funcionalidade será implementada em breve.");
                case 5 -> System.out.println("Essa funcionalidade será implementada em breve.");
                case 6 -> System.out.println("Essa funcionalidade será implementada em breve.");
                case 7 -> System.out.println("Essa funcionalidade será implementada em breve.");
                case 8 -> System.out.println("Essa funcionalidade será implementada em breve.");
                case 0 -> System.out.println("Saindo do menu do usuário...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // RF03 - Cadastro de livros
    private void cadastrarLivro(Usuario usuario) {
        System.out.println("\n--- Cadastrar Novo Livro ---");

        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Autor: ");
        String autor = sc.nextLine();

        System.out.print("Categoria/Gênero: ");
        String categoria = sc.nextLine();

        System.out.println("\nStatus de leitura:");
        System.out.println("1 - Não lido");
        System.out.println("2 - Lendo");
        System.out.println("3 - Lido");
        System.out.print("Escolha o status: ");
        int statusOpcao = sc.nextInt();
        sc.nextLine(); // limpa o buffer

        String statusLeitura;
        switch (statusOpcao) {
            case 1 -> statusLeitura = "não lido";
            case 2 -> statusLeitura = "lendo";
            case 3 -> statusLeitura = "lido";
            default -> {
                System.out.println("Opção inválida! Definindo como 'não lido'.");
                statusLeitura = "não lido";
            }
        }

        boolean sucesso = livroController.cadastrarLivro(titulo, autor, categoria, statusLeitura, usuario.getId());

        if (sucesso) {
            System.out.println("\n✓ Livro cadastrado com sucesso no seu acervo pessoal!");
        } else {
            System.out.println("\n✗ Erro ao cadastrar o livro. Tente novamente.");
        }
    }

    // RF04 - Lista de livros
    private void listarLivros(Usuario usuario) {
        System.out.println("\n--- Meus Livros ---");

        List<Livro> livros = livroController.listarLivros(usuario.getId());

        if (livros.isEmpty()) {
            System.out.println("Você ainda não possui livros cadastrados.");
            System.out.println("Cadastre seu primeiro livro na opção 1 do menu!");
        } else {
            System.out.println("\nTotal de livros: " + livros.size());
            System.out.println("=".repeat(80));

            for (Livro livro : livros) {
                System.out.println(livro.toString());
                System.out.println("-".repeat(80));
            }
        }
    }
}