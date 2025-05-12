package jdev.mentoria.lojavirtual.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jdev.mentoria.lojavirtual.ExceptionMentoriaJava;
import jdev.mentoria.lojavirtual.model.MarcaProduto;
import jdev.mentoria.lojavirtual.repository.MarcaRepository;

@Controller
@RestController
public class MarcaProdutoController {

	@Autowired
	private MarcaRepository marcaRepository;

	
    @Operation(summary = "Realizar o cadastro da Marca de um Produto", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro de Marca Produto realizado com sucesso"),  
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro de Marca Produto"),
    })
	@ResponseBody
	@PostMapping(value = "**/salvarMarca")
	public ResponseEntity<MarcaProduto> salvarMarca(@RequestBody @Valid MarcaProduto marcaProduto)
			throws ExceptionMentoriaJava { /* Recebe o JSON e converte pra Objeto */

		if (marcaProduto.getId() == null) {
			List<MarcaProduto> marcaProdutos = marcaRepository
					.buscarMarcaDesc(marcaProduto.getNomeDesc().toUpperCase());

			if (!marcaProdutos.isEmpty()) {
				throw new ExceptionMentoriaJava("Já existe Marca com a descrição/nome: " + marcaProduto.getNomeDesc());
			}
		}

		MarcaProduto marcaProdutoSalvo = marcaRepository.save(marcaProduto);

		return new ResponseEntity<MarcaProduto>(marcaProdutoSalvo, HttpStatus.OK);
	}

    @Operation(summary = "Deletar Marca Produto", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Deletar MarcaProduto realizado com sucesso"),  
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar ao deletar Marca Produto"),
    })
	@ResponseBody
	@PostMapping(value = "**/deleteMarca")
	public ResponseEntity<?> deleteMarca(
			@RequestBody MarcaProduto marcaProduto) { /* Recebe o JSON e converte pra Objeto */

		marcaRepository.deleteById(marcaProduto.getId());

		return new ResponseEntity("Marca produto Removido", HttpStatus.OK);
	}
    
    
    @Operation(summary = "Deletar MarcaProduto por Id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Marca Produto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Marca Produto não encontrado"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar Marca Produto"),
    })
	@ResponseBody
	@DeleteMapping(value = "**/deleteMarcaPorId/{id}")
	public ResponseEntity<?> deleteMarcaPorId(@PathVariable("id") Long id) {

		marcaRepository.deleteById(id);

		return new ResponseEntity("Marca Produto Removido", HttpStatus.OK);
	}

	
    @Operation(summary = "Obter Marca Produto por Id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
	@ResponseBody
	@GetMapping(value = "**/obterMarcaProduto/{id}")
	public ResponseEntity<MarcaProduto> obterMarcaProduto(@PathVariable("id") Long id) throws ExceptionMentoriaJava {

		MarcaProduto marcaProduto = marcaRepository.findById(id).orElse(null);

		if (marcaProduto == null) {
			throw new ExceptionMentoriaJava("Não encontrou Marca Produto com código: " + id);
		}

		return new ResponseEntity<MarcaProduto>(marcaProduto, HttpStatus.OK);
	}

    
    @Operation(summary = "Obter MarcaProduto por descricao(nome)", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
	@ResponseBody
	@GetMapping(value = "**/buscarMarcaProdutoPorDesc/{desc}")
	public ResponseEntity<List<MarcaProduto>> buscarMarcaProdutoPorDesc(@PathVariable("desc") String desc) {

		List<MarcaProduto> marcaProdutos = marcaRepository.buscarMarcaDesc(desc.toUpperCase().trim());

		return new ResponseEntity<List<MarcaProduto>>(marcaProdutos, HttpStatus.OK);
	}

}
