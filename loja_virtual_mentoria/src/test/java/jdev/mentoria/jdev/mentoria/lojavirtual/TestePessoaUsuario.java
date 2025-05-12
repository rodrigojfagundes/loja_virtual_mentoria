package jdev.mentoria.lojavirtual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import jdev.mentoria.lojavirtual.ExceptionMentoriaJava;
import jdev.mentoria.lojavirtual.LojaVirtualMentoriaApplication;
import jdev.mentoria.lojavirtual.controller.CupDescontoController;
import jdev.mentoria.lojavirtual.controller.FormaPagamentoController;
import jdev.mentoria.lojavirtual.controller.PessoaController;
import jdev.mentoria.lojavirtual.enums.TipoEndereco;
import jdev.mentoria.lojavirtual.model.Endereco;
import jdev.mentoria.lojavirtual.model.PessoaFisica;
import jdev.mentoria.lojavirtual.model.PessoaJuridica;
import jdev.mentoria.lojavirtual.repository.PesssoaRepository;
import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
public class TestePessoaUsuario extends TestCase {

	@Autowired
	private FormaPagamentoController formaPagamentoController;

	@Autowired
	private PessoaController pessoaController;

	@Autowired
	private PesssoaRepository pessoaRepository;

	@Autowired
	private CupDescontoController cupDescontoController;

	@Test
	public void testCupDesconto() {
		cupDescontoController.listaCupomDesc();
		cupDescontoController.listaCupomDesc(1L);
	}

	@Test
	public void testFormaPagamento() {
		formaPagamentoController.listaFormaPagamento();
		formaPagamentoController.listaFormaPagamentoidEmpresa(1L);
	}

	@Test
	public void testCadPessoaJuridica() throws ExceptionMentoriaJava {

		PessoaJuridica pessoaJuridica = new PessoaJuridica();

		pessoaJuridica.setCnpj("93.918.688/0001-74");
		pessoaJuridica.setNome("Alex Fando");
		pessoaJuridica.setEmail("lexo@gmail.com");
		pessoaJuridica.setTelefone("479775800");
		pessoaJuridica.setInscEstadual("64324732565");
		pessoaJuridica.setInscMunicipal("54447324325");
		pessoaJuridica.setNomeFantasia("739797834234322");
		pessoaJuridica.setRazaoSocial("648268744324327");

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

		for (Endereco endereco : pessoaJuridica.getEnderecos()) {
			assertEquals(true, endereco.getId() > 0);
		}

		assertEquals(2, pessoaJuridica.getEnderecos().size());

	}

	@Test
	public void testCadPessoaFisica() throws ExceptionMentoriaJava {

		PessoaJuridica pessoaJuridica = pessoaRepository.existeCnpjCadastrado("1732651750286");

		PessoaFisica pessoaFisica = new PessoaFisica();
		pessoaFisica.setCpf("873.894.380-88");
		pessoaFisica.setNome("Alex Fando");
		pessoaFisica.setEmail("rodrigo27@gmail.com");
		pessoaFisica.setTelefone("459977975800");
		pessoaFisica.setEmpresa(pessoaJuridica);

		Endereco endereco1 = new Endereco();
		endereco1.setBairro("Bairro Aqui");
		endereco1.setCep("7777");
		endereco1.setComplemento("Casa");
		endereco1.setNumero("28");

		endereco1.setPessoa(pessoaFisica);
		endereco1.setRuaLogra("Avenida Brasil");
		endereco1.setTipoEndereco(TipoEndereco.COBRANCA);
		endereco1.setUf("SC");
		endereco1.setCidade("Balneario Camboriu");

		endereco1.setEmpresa(pessoaJuridica);

		Endereco endereco2 = new Endereco();
		endereco2.setBairro("Bairro Centro");
		endereco2.setCep("777742");
		endereco2.setComplemento("Apartamento");
		endereco2.setNumero("35");

		endereco2.setPessoa(pessoaFisica);
		endereco2.setRuaLogra("Avenida Brasil");
		endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
		endereco2.setUf("SC");
		endereco2.setCidade("Tijucas");

		endereco2.setEmpresa(pessoaJuridica);

		pessoaFisica.getEnderecos().add(endereco2);
		pessoaFisica.getEnderecos().add(endereco1);

		pessoaFisica = pessoaController.salvarPf(pessoaFisica).getBody();

		assertEquals(true, pessoaFisica.getId() > 0);

		for (Endereco endereco : pessoaFisica.getEnderecos()) {
			assertEquals(true, endereco.getId() > 0);
		}

		assertEquals(2, pessoaFisica.getEnderecos().size());

	}

}
