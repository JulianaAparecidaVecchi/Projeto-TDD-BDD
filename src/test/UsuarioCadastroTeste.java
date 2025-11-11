package test;

import controller.UsuarioController;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UsuarioCadastroTeste {

    private UsuarioController controller;

    @Before
    public void setUp() {
        controller = new UsuarioController();
    }

    @Test
    public void deveCadastrarUsuarioComDadosValidos() {
        String nome = "Juliana Aparecida Vecchi";
        String email = "julianaaparecidavecchi@gmail.com";
        String senha = "123";
        boolean resultado = controller.cadastrar(nome, email, senha);
        assertTrue("O usuário deve ser cadastrado com sucesso", resultado);
    }
}
