package jdev.mentoria.lojavirtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jdev.mentoria.lojavirtual.service.ServiceJunoBoleto;

@RestController
public class AsaasController {

	@Autowired
	private ServiceJunoBoleto serviceJunoBoleto;

	
    @Operation(summary = "Metodo para gerar chave PIX na API Asaas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Geração realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
	@GetMapping(value = "/gerarChavePix")
	public String gerarChave() throws Exception {
		String chaveApi = serviceJunoBoleto.criarChavePixAsaas();

		System.out.println("Chave Asaas API" + chaveApi);

		return chaveApi.toString();
	}

}
