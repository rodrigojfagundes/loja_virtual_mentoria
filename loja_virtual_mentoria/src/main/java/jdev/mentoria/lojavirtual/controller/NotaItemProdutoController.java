package jdev.mentoria.lojavirtual.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jdev.mentoria.lojavirtual.ExceptionMentoriaJava;
import jdev.mentoria.lojavirtual.model.NotaItemProduto;
import jdev.mentoria.lojavirtual.repository.NotaItemProdutoRepository;

@RestController
public class NotaItemProdutoController {

	@Autowired
	private NotaItemProdutoRepository notaItemProdutoRepository;

    @Operation(summary = "Cadastrando a Nota Fiscal de um Produto", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso"),  
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro Nota Fiscal de Produto"),
    })
	@ResponseBody
	@PostMapping(value = "**/salvarNotaItemProduto")
	public ResponseEntity<NotaItemProduto> salvarNotaItemProduto(@RequestBody @Valid NotaItemProduto notaItemProduto)
			throws ExceptionMentoriaJava {

		if (notaItemProduto.getId() == null) {

			if (notaItemProduto.getProduto() == null || notaItemProduto.getProduto().getId() <= 0) {
				throw new ExceptionMentoriaJava("O produto deve ser informado.");
			}

			if (notaItemProduto.getNotaFiscalCompra() == null || notaItemProduto.getNotaFiscalCompra().getId() <= 0) {
				throw new ExceptionMentoriaJava("A nota fisca deve ser informada.");
			}

			if (notaItemProduto.getEmpresa() == null || notaItemProduto.getEmpresa().getId() <= 0) {
				throw new ExceptionMentoriaJava("A empresa deve ser informada.");
			}

			List<NotaItemProduto> notaExistente = notaItemProdutoRepository.buscaNotaItemPorProdutoNota(
					notaItemProduto.getProduto().getId(), notaItemProduto.getNotaFiscalCompra().getId());

			if (!notaExistente.isEmpty()) {
				throw new ExceptionMentoriaJava("Já existe este produto cadastrado para esta nota.");
			}

		}

		if (notaItemProduto.getQuantidade() <= 0) {
			throw new ExceptionMentoriaJava("A quantidade do produto deve ser informada.");
		}

		NotaItemProduto notaItemSalva = notaItemProdutoRepository.save(notaItemProduto);

		notaItemSalva = notaItemProdutoRepository.findById(notaItemProduto.getId()).get();

		return new ResponseEntity<NotaItemProduto>(notaItemSalva, HttpStatus.OK);

	}

    @Operation(summary = "Deletando Nota Item Produto por Id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Nota Item Produto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nota Item Produto não encontrado"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar Nota Item Produto"),
    })
	@ResponseBody
	@DeleteMapping(value = "**/deleteNotaItemPorId/{id}")
	public ResponseEntity<?> deleteNotaItemPorId(@PathVariable("id") Long id) {

		notaItemProdutoRepository.deleteByIdNotaItem(id);

		return new ResponseEntity("Nota Item Produto Removido", HttpStatus.OK);
	}

}
