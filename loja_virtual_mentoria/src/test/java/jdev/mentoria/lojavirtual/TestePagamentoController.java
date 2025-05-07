package jdev.mentoria.lojavirtual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import jdev.mentoria.lojavirtual.controller.PagamentoController;
import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
public class TestePagamentoController extends TestCase {

	@Autowired
	private PagamentoController pagamentoController;

	@Test
	public void testfinalizarCompraCartaoAsaas() throws Exception {
		pagamentoController.finalizarCompraCartaoAsaas("5126462892278565", "NOME CLIENTE 9", "612", "06", "2025", 27L,
				"68409959097", 2, "75830112", "Rua Barbosa OK", "62", "PR", "Curitiba");
	}

}
