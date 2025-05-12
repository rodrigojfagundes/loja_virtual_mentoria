package jdev.mentoria.lojavirtual.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import jdev.mentoria.lojavirtual.model.ImagemProduto;
import jdev.mentoria.lojavirtual.model.dto.ImagemProdutoDTO;
import jdev.mentoria.lojavirtual.repository.ImagemProdutoRepository;

@RestController
public class ImagemProdutoController {

	@Autowired
	private ImagemProdutoRepository imagemProdutoRepository;

    @Operation(summary = "Realiza o cadastro de Imagem de Produto", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro de Imagem de Produtp realizado com sucesso"),  
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro de Imagem de Produto"),
    })
	@ResponseBody
	@PostMapping(value = "**/salvarImagemProduto")
	public ResponseEntity<ImagemProdutoDTO> salvarImagemProduto(@RequestBody ImagemProduto imagemProduto) {

		imagemProduto = imagemProdutoRepository.saveAndFlush(imagemProduto);

		ImagemProdutoDTO imagemProdutoDTO = new ImagemProdutoDTO();
		imagemProdutoDTO.setId(imagemProduto.getId());
		imagemProdutoDTO.setEmpresa(imagemProduto.getEmpresa().getId());
		imagemProdutoDTO.setProduto(imagemProduto.getProduto().getId());
		imagemProdutoDTO.setImagemMiniatura(imagemProduto.getImagemMiniatura());
		imagemProdutoDTO.setImagemOriginal(imagemProduto.getImagemOriginal());

		return new ResponseEntity<ImagemProdutoDTO>(imagemProdutoDTO, HttpStatus.OK);

	}

	@ResponseBody
	@DeleteMapping(value = "**/deleteTodoImagemProduto/{idProduto}")
	public ResponseEntity<?> deleteTodoImagemProduto(@PathVariable("idProduto") Long idProduto) {

		imagemProdutoRepository.deleteImagem(idProduto);

		return new ResponseEntity<String>("Imagems do produto removida", HttpStatus.OK);
	}

    @Operation(summary = "Delete Imagem de Produto", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Imagem de Produto deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Imagem de Produto não encontrado"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar Imagem de Produto"),
    })
	@ResponseBody
	@DeleteMapping(value = "**/deleteImagemObjeto")
	public ResponseEntity<?> deleteImagemProdutoPorId(@RequestBody ImagemProduto imagemProduto) {

		if (!imagemProdutoRepository.existsById(imagemProduto.getId())) {
			return new ResponseEntity<String>(
					"Imagem já foi removida ou não existe com esse id: " + imagemProduto.getId(), HttpStatus.OK);
		}

		imagemProdutoRepository.deleteById(imagemProduto.getId());

		return new ResponseEntity<String>("Imagem removida", HttpStatus.OK);
	}

    @Operation(summary = "Deleta imagem de produto por Id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Imagem de Produto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Imagem de Produto não encontrado"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar Imagme de Produto"),
    })
	@ResponseBody
	@DeleteMapping(value = "**/deleteImagemProdutoPorId/{id}")
	public ResponseEntity<?> deleteImagemProdutoPorId(@PathVariable("id") Long id) {

		if (!imagemProdutoRepository.existsById(id)) {
			return new ResponseEntity<String>("Imagem já foi removida ou não existe com esse id: " + id, HttpStatus.OK);
		}

		imagemProdutoRepository.deleteById(id);

		return new ResponseEntity<String>("Imagem removida", HttpStatus.OK);
	}

    
    
    @Operation(summary = "Busca Imagem de Produto por Id do Produto", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
	@ResponseBody
	@GetMapping(value = "**/obterImagemPorProduto/{idProduto}")
	public ResponseEntity<List<ImagemProdutoDTO>> obterImagemPorProduto(@PathVariable("idProduto") Long idProduto) {

		List<ImagemProdutoDTO> dtos = new ArrayList<ImagemProdutoDTO>();

		List<ImagemProduto> imagemProdutos = imagemProdutoRepository.buscaImagemProduto(idProduto);

		for (ImagemProduto imagemProduto : imagemProdutos) {

			ImagemProdutoDTO imagemProdutoDTO = new ImagemProdutoDTO();
			imagemProdutoDTO.setId(imagemProduto.getId());
			imagemProdutoDTO.setEmpresa(imagemProduto.getEmpresa().getId());
			imagemProdutoDTO.setProduto(imagemProduto.getProduto().getId());
			imagemProdutoDTO.setImagemMiniatura(imagemProduto.getImagemMiniatura());
			imagemProdutoDTO.setImagemOriginal(imagemProduto.getImagemOriginal());

			dtos.add(imagemProdutoDTO);
		}

		return new ResponseEntity<List<ImagemProdutoDTO>>(dtos, HttpStatus.OK);

	}

}
