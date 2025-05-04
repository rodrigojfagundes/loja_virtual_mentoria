package jdev.mentoria.lojavirtual;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import jdev.mentoria.lojavirtual.controller.PessoaController;
import jdev.mentoria.lojavirtual.enums.TipoEndereco;
import jdev.mentoria.lojavirtual.model.Endereco;
import jdev.mentoria.lojavirtual.model.PessoaFisica;
import jdev.mentoria.lojavirtual.model.PessoaJuridica;
import jdev.mentoria.lojavirtual.repository.PessoaRepository;
import jdev.mentoria.lojavirtual.service.PessoaUserService;
import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
public class TestePessoaUsuario extends TestCase {
	
	@Autowired
	private PessoaController pessoaController;
	


	
	
	@Test
	public void testCadPessoaFisica() throws ExceptionMentoriaJava {
		
		
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setCnpj("" + Calendar.getInstance().getTimeInMillis());
		pessoaJuridica.setNome("Alex Fando");
		pessoaJuridica.setEmail("abcc@gmail.com");
		pessoaJuridica.setTelefone("459977975800");
		pessoaJuridica.setInscEstadual("6565");
		pessoaJuridica.setInscMunicipal("5445");
		pessoaJuridica.setNomeFantasia("73979832");
		pessoaJuridica.setRazaoSocial("64826847");
		
		Endereco endereco1 = new Endereco();
		endereco1.setBairro("Bairro Aqui");
		endereco1.setCep("7777");
		endereco1.setComplemento("Casa");
		
		endereco1.setEmpresa(pessoaJuridica);
		endereco1.setNumero("28");
		
		endereco1.setPessoa(pessoaJuridica);
		endereco1.setRuaLogra("Avenida Brasil");
		endereco1.setTipoEndereco(TipoEndereco.COBRANCA);
		endereco1.setUf("SC");
		endereco1.setCidade("Balneario Camboriu");
		
		
		Endereco endereco2 = new Endereco();
		endereco2.setBairro("Bairro Centro");
		endereco2.setCep("777742");
		endereco2.setComplemento("Apartamento");
		
		endereco2.setEmpresa(pessoaJuridica);
		endereco2.setNumero("35");
		
		
		
		endereco2.setPessoa(pessoaJuridica);
		endereco2.setRuaLogra("Avenida Brasil");
		endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
		endereco2.setUf("SC");
		endereco2.setCidade("Tijucas");
		
		
		pessoaJuridica.getEnderecos().add(endereco2);
		pessoaJuridica.getEnderecos().add(endereco1);
		
		pessoaJuridica = pessoaController.salvarPj(pessoaJuridica).getBody();
		
		assertEquals(true, pessoaJuridica.getId() > 0);
		
		for(Endereco endereco : pessoaJuridica.getEnderecos()) {
			assertEquals(true, endereco.getId() > 0);
		}
		
		assertEquals(2, pessoaJuridica.getEnderecos().size());
		
		
		
			
		
	}
	
	
	
}
