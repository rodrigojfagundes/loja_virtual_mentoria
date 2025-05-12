package jdev.mentoria.lojavirtual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jdev.mentoria.lojavirtual.model.StatusRastreio;
import jdev.mentoria.lojavirtual.repository.StatusRastreioRepository;

@RestController
public class StatusRastreioController {

	@Autowired
	private StatusRastreioRepository statusRastreioRepository;

    @Operation(summary = "Busca de Status de Rastreio por Id de VendaCompraLojaVirtual", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
	@ResponseBody
	@GetMapping(value = "**/listaRastreioVenda/{idVenda}")
	public ResponseEntity<List<StatusRastreio>> listaRastreioVenda(@PathVariable("idVenda") Long idVenda) {

		List<StatusRastreio> statusRastreios = statusRastreioRepository.listaRastreioVenda(idVenda);

		return new ResponseEntity<List<StatusRastreio>>(statusRastreios, HttpStatus.OK);

	}

}
