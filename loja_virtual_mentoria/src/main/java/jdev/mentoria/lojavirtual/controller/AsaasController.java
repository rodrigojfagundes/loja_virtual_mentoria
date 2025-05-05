package jdev.mentoria.lojavirtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jdev.mentoria.lojavirtual.service.ServiceJunoBoleto;

@RestController
public class AsaasController {

	@Autowired
	private ServiceJunoBoleto serviceJunoBoleto;

	@GetMapping(value = "/gerarChavePix")
	public String gerarChave() throws Exception {
		String chaveApi = serviceJunoBoleto.criarChavePixAsaas();

		System.out.println("Chave Asaas API" + chaveApi);

		return chaveApi.toString();
	}

}
