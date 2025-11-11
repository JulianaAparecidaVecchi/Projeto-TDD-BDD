package controller;

import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioController {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public boolean cadastrar(String nome, String email, String senha) {
        Usuario novoUsuario = new Usuario(nome, email, senha);
        return usuarioDAO.salvarUsuario(novoUsuario);
    }

    public Usuario login(String email, String senha) {
        return usuarioDAO.buscarPorEmailESenha(email, senha);
    }
}
