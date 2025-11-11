package test;

import controller.UsuarioController;
import model.Usuario;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UsuarioLoginTeste {

    private UsuarioController controller;

    @Before
    public void setUp() {
        controller = new UsuarioController();
    }

    @Test
    public void permitirLoginComCredenciaisValidas() {
        Usuario usuarioLogado = controller.login("julianaaparecidavecchi@gmail.com", "123");
        assertNotNull("O usuário deve ser autenticado com sucesso", usuarioLogado);}
}
